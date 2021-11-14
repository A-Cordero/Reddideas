package com.aridev.cordero.twitdeas.ui.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.aridev.cordero.twitdeas.databinding.ActivityLaunchBinding
import com.aridev.cordero.twitdeas.ui.router.Router

class LaunchActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLaunchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaunchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Handler(Looper.getMainLooper()).postDelayed({
            Router.launch(this,MainActivity())
        },1500)
    }
}