package com.aridev.cordero.twitdeas.ui.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.aridev.cordero.twitdeas.R
import com.aridev.cordero.twitdeas.core.extensions.bounce
import com.aridev.cordero.twitdeas.core.language
import com.aridev.cordero.twitdeas.core.style
import com.aridev.cordero.twitdeas.data.model.app.Idea
import com.aridev.cordero.twitdeas.databinding.ItemMyIdeaBinding

class MyIdeasAdapter : RecyclerView.Adapter<MyIdeasAdapter.MyViewHolder>() {
    var list = ArrayList<Idea>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class  MyViewHolder(val binding : ItemMyIdeaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.binding.apply {
            if(style == "dark") {
                textAuthor.setTextColor(ContextCompat.getColor(textAuthor.context, R.color.black))
                textBody.setTextColor(ContextCompat.getColor(textAuthor.context, R.color.black))
                textLikes.setTextColor(ContextCompat.getColor(textAuthor.context, R.color.black))
            } else {
                textAuthor.setTextColor(ContextCompat.getColor(textAuthor.context, R.color.white))
                textBody.setTextColor(ContextCompat.getColor(textAuthor.context, R.color.white))
                textLikes.setTextColor(ContextCompat.getColor(textAuthor.context, R.color.white))
            }
            if(list[position].author != "[deleted]") {
                textAuthor.text = list[position].author
            } else {
                textAuthor.text = ""
            }


            textBody.text = list[position].body


            textCommnet.text = list[position].comment
            textLikes.text = list[position].ups.toString()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemMyIdeaBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }
    override fun getItemCount() = list.size

}