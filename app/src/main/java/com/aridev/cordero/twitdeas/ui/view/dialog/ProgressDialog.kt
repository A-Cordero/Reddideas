package com.aridev.cordero.twitdeas.ui.view.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import com.aridev.cordero.twitdeas.databinding.ProgressDialogBinding


class ProgressDialog (context: Context) : Dialog(context) {

    init {
        setCancelable(false)
    }
    private lateinit var binding: ProgressDialogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = ProgressDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        //binding.imgProgress.loadUrlImageDrawable(R.raw.load)
    }
}