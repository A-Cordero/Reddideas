package com.aridev.cordero.twitdeas.ui.view.activities

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.aridev.cordero.twitdeas.R
import com.aridev.cordero.twitdeas.core.extensions.getCenterView
import com.aridev.cordero.twitdeas.core.style
import com.aridev.cordero.twitdeas.databinding.ActivityMainBinding
import com.aridev.cordero.twitdeas.ui.router.Router
import com.aridev.cordero.twitdeas.ui.view.fragments.ListIdeasFragment
import com.aridev.cordero.twitdeas.ui.view.fragments.MyIdeasFragment
import com.aridev.cordero.twitdeas.ui.viewModel.ActivityViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: ActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[ActivityViewModel::class.java]
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setView()
        setListeners()
        setObservers()
    }

    private fun setListeners() {
        binding.apply {

            ctLight.setOnClickListener {
                var centerPoint = binding.pointButtonBar.getCenterView()

                var centerGuide = binding.gLeft.getCenterView()
                moveAnimation(
                    binding.gLeft.x,
                    centerGuide - centerPoint + binding.pointButtonBar.translationX,
                    binding.btnLight
                )

                setLight()
            }

            ctNight.setOnClickListener {
                var centerPoint = binding.pointButtonBar.getCenterView()

                var centerGuide = binding.gCenter.getCenterView()

                moveAnimation(
                    binding.gCenter.x,
                    centerGuide - centerPoint + binding.pointButtonBar.translationX,
                    binding.btnNight
                )
                setNight()
            }

            ctIdea.setOnClickListener {
                var centerPoint = binding.pointButtonBar.getCenterView()

                var centerGuide = binding.gRight.getCenterView()

                moveAnimation(
                    binding.gRight.x,
                    centerGuide - centerPoint + binding.pointButtonBar.translationX,
                    binding.btnIdeas
                )
                Router.replace(MyIdeasFragment(),supportFragmentManager,R.id.container_fragments)
            }
        }
    }

    private fun setNight() {
        style = "light"
        binding.containerNight.visibility = View.VISIBLE
        binding.containerLight.visibility = View.GONE
        Router.replace(ListIdeasFragment(),supportFragmentManager,R.id.container_fragments)

    }

    private fun setLight() {
        style = "dark"
        binding.containerNight.visibility = View.GONE
        binding.containerLight.visibility = View.VISIBLE
        Router.replace(ListIdeasFragment(),supportFragmentManager,R.id.container_fragments)

    }

    private fun setObservers() {

    }


    private fun setView() {
        Router.replace(ListIdeasFragment(), supportFragmentManager, R.id.container_fragments)
    }

    private fun moveAnimation(newX: Float, delta: Float, view: ImageView) {

        ObjectAnimator.ofFloat(binding.pointButtonBar, "translationX", delta).apply {
            duration = 380

            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationStart(animation: Animator?) {
                    super.onAnimationStart(animation)
                    setEnableBtn(false)
                }

                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    whiteAllBtn()
                    view.setColorFilter(
                        ContextCompat.getColor(
                            this@MainActivity,
                            R.color.orangeButtonBar
                        )
                    )
                    setEnableBtn(true)
                }
            })
            start()
        }

    }

    private fun setEnableBtn(flag: Boolean) {
        binding.apply {
            ctLight.isEnabled = flag
            ctNight.isEnabled = flag
            ctIdea.isEnabled = flag
        }
    }

    private fun whiteAllBtn() {
        binding.apply {
            btnLight.setColorFilter(ContextCompat.getColor(this@MainActivity, R.color.white))
            btnNight.setColorFilter(ContextCompat.getColor(this@MainActivity, R.color.white))
            btnIdeas.setColorFilter(ContextCompat.getColor(this@MainActivity, R.color.white))
        }
    }
}