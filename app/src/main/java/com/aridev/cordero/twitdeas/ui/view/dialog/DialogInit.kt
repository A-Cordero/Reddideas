package com.aridev.cordero.twitdeas.ui.view.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import com.aridev.cordero.twitdeas.databinding.DialaogAddIdeaBinding
import com.aridev.cordero.twitdeas.databinding.InitDialogBinding

class DialogInit (context: Context, callback:(success:String)->Unit): Dialog(context){
    private var myContext: Context =context
    private lateinit var binding: InitDialogBinding
    init {
        initDialog(callback)
    }

    private fun initDialog(callback: (success: String) -> Unit) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.setCancelable(false)
        binding = InitDialogBinding.inflate(LayoutInflater.from(myContext))
        this.setContentView(binding.root)
        this.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        this.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        binding.btnOkAlert.setOnClickListener{
            this.dismiss()
        }

    }
}