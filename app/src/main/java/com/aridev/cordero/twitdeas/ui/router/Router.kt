package com.aridev.cordero.twitdeas.ui.router

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

object Router : ActivityRouter, FragmentRouter {
}

interface ActivityRouter {
    // Activity
    fun intent(context: Context, activity: Activity): Intent = Intent(context, activity::class.java)

    fun launch(context: Context, activity: Activity) = context.startActivity(intent(context,activity))
}

interface FragmentRouter {
    fun add(fragment : Fragment, manager: FragmentManager, containerId: Int, tag: String) = manager.beginTransaction().add(containerId, fragment, tag).addToBackStack(null).commitAllowingStateLoss()

    fun replace(fragment : Fragment, manager: FragmentManager, containerId: Int) = manager.beginTransaction().replace(containerId, fragment).commit()

    fun show(fragment : Fragment, manager: FragmentManager) = manager.beginTransaction().show(fragment).commitAllowingStateLoss()

    fun hide(fragment : Fragment, manager: FragmentManager) = manager.beginTransaction().hide(fragment).commitAllowingStateLoss()

    fun remove(fragment : Fragment, manager: FragmentManager) = manager.beginTransaction().remove(fragment).commitAllowingStateLoss()
}