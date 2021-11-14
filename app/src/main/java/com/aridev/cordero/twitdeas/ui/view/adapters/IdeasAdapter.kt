package com.aridev.cordero.twitdeas.ui.view.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.aridev.cordero.twitdeas.R
import com.aridev.cordero.twitdeas.core.extensions.bounce
import com.aridev.cordero.twitdeas.core.language
import com.aridev.cordero.twitdeas.core.style
import com.aridev.cordero.twitdeas.data.model.app.Idea
import com.aridev.cordero.twitdeas.data.model.response.ChildData
import com.aridev.cordero.twitdeas.databinding.ItemIdeaBinding
import com.google.mlkit.nl.translate.Translator

class IdeasAdapter : RecyclerView.Adapter<IdeasAdapter.MyViewHolder>() {
    lateinit var callback :((idea : Idea) -> Unit)
    var list = ArrayList<ChildData>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    lateinit var translator: Translator

    inner class  MyViewHolder(val binding : ItemIdeaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.binding.apply {

            if(style == "dark") {
                textAuthor.setTextColor(ContextCompat.getColor(textAuthor.context,R.color.black))
                textBody.setTextColor(ContextCompat.getColor(textAuthor.context,R.color.black))
                textLikes.setTextColor(ContextCompat.getColor(textAuthor.context,R.color.black))
            } else {
                textAuthor.setTextColor(ContextCompat.getColor(textAuthor.context,R.color.white))
                textBody.setTextColor(ContextCompat.getColor(textAuthor.context,R.color.white))
                textLikes.setTextColor(ContextCompat.getColor(textAuthor.context,R.color.white))
            }
            if(list[position].data.author != "[deleted]") {
                textAuthor.text = list[position].data.author
            } else {
                textAuthor.text = ""
            }

            if(language == "ES") {
                translator.translate(list[position].data.body)
                    .addOnSuccessListener { translatedText ->
                        textBody.text = translatedText
                    }
                    .addOnFailureListener { exception ->
                        textBody.text = list[position].data.body
                    }
            } else {
                textBody.text = list[position].data.body
            }


            textLikes.text = list[position].data.ups.toString()

            containerIdea.setOnClickListener {
                containerIdea.isEnabled = false
                containerIdea.bounce {
                    containerIdea.isEnabled = true
                    callback.invoke(list[position].data)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemIdeaBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }
    override fun getItemCount() = list.size

}