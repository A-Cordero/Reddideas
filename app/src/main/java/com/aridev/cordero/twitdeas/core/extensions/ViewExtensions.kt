package com.aridev.cordero.twitdeas.core.extensions

import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.cardview.widget.CardView
import com.aridev.cordero.twitdeas.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

fun ImageView.loadUrlImage(url:String){
    Glide.with(context)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .fitCenter()
        .into(this)
}

 fun View.getCenterView(): Float{
     val location = IntArray(2)
     this.getLocationOnScreen(location)
     val posX = location[0]
     val posY = location[1]
     val width  = this.width
    return posX.toFloat() + width/2
}

fun View.bounce(callback : (finish: Boolean) -> Unit) {
    val animation = AnimationUtils.loadAnimation(this.context, R.anim.bounce)
    this.startAnimation(animation)
    Handler(Looper.getMainLooper()).postDelayed(
        {
            callback.invoke(true)
        },300)
}