package com.aridev.cordero.twitdeas.aplication

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.aridev.cordero.twitdeas.core.RetrofitHelper
import com.aridev.cordero.twitdeas.data.retrofitRepository.ApiClient
import com.aridev.cordero.twitdeas.ui.view.adapters.IdeasAdapter
import com.aridev.cordero.twitdeas.ui.view.adapters.MyIdeasAdapter
import com.aridev.cordero.twitdeas.ui.viewModel.ActivityViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private const val PREFERENCE_NAME = "preference"

val appModule = module {
    viewModel { ActivityViewModel() }
    single { RetrofitHelper.getRetrofit().create(ApiClient::class.java) }
    single { provideSharedPref(androidApplication()) }
    factory { IdeasAdapter() }
    factory { MyIdeasAdapter() }

}


fun provideSharedPref(app: Application): SharedPreferences {
    return app.applicationContext.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
}