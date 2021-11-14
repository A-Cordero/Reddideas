package com.aridev.cordero.twitdeas.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aridev.cordero.twitdeas.data.model.app.Idea
import com.aridev.cordero.twitdeas.data.preferenceRepository.PreferenceRepository

class MyIdeasViewModel : ViewModel() {
    private val _listIdeas = MutableLiveData<ArrayList<Idea>>()
    val listIdeas : LiveData<ArrayList<Idea>> = _listIdeas

    private val preferences = PreferenceRepository()

    fun getIdeas() {
        _listIdeas.value = preferences.getIdeas() ?: arrayListOf()
    }
}