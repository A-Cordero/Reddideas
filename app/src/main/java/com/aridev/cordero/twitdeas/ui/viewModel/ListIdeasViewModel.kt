package com.aridev.cordero.twitdeas.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aridev.cordero.twitdeas.core.Tags
import com.aridev.cordero.twitdeas.core.language
import com.aridev.cordero.twitdeas.data.model.app.Idea
import com.aridev.cordero.twitdeas.data.model.app.ListIdeas
import com.aridev.cordero.twitdeas.data.model.response.ChildData
import com.aridev.cordero.twitdeas.data.preferenceRepository.PreferenceRepository
import com.aridev.cordero.twitdeas.domain.GetIdeas

class ListIdeasViewModel : ViewModel() {
    private val _listIdeas = MutableLiveData<ArrayList<ChildData>>()
    val listIdeas : LiveData<ArrayList<ChildData>> = _listIdeas

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _cancelLoading = MutableLiveData<Boolean>()
    val cancelLoading : LiveData<Boolean> = _cancelLoading

    private val _errorApp = MutableLiveData<String>()
    val errorApp : LiveData<String> = _errorApp

    private val _changeLanguageEs= MutableLiveData<Boolean>()
    val changeLanguageEs : LiveData<Boolean> = _changeLanguageEs

    private val _changeLanguageEn = MutableLiveData<Boolean>()
    val changeLanguageEn : LiveData<Boolean> = _changeLanguageEn

    private val _isEs = MutableLiveData<Boolean>()
    val isEs : LiveData<Boolean> = _isEs

    private val totalIdeas = arrayListOf<ChildData>()
    private val getIdeas = GetIdeas()
    private val preferences = PreferenceRepository()
    private var cursor : String ?= ""

    fun init() {
        if( language == "EN") {
            getIdeas()
        } else {
            _isEs.value = true
        }
    }
    fun getIdeas() {
        _isLoading.value = true
        getIdeas(Tags.APPS.url+cursor){ success, error ->
            if(error.isNullOrEmpty()) {
                if(success!!.children.size > 0 ){
                    cursor = success!!.children[0].subredditId
                }
                totalIdeas.addAll(success.children)
                _listIdeas.value = totalIdeas
                _cancelLoading.value = true
            } else {
                _errorApp.value = error
                _cancelLoading.value = true
            }

        }
    }

    fun changeLanguage(checked : Boolean) {
        if(checked) {
            language = "ES"
            _changeLanguageEs.value = true
        } else {
            language = "EN"
            _changeLanguageEn.value = true
        }
    }

    fun addIdea(idea : Idea) {
        var listIdeas = ListIdeas()
        var ideas  : ArrayList<Idea> = preferences.getIdeas() ?: arrayListOf()
        ideas.add(idea)

        ideas.forEach{
            listIdeas.add(it)
        }
        preferences.saveIdea(listIdeas)
        _errorApp.value = "Una nueva idea para el futuro ha sido guardada."
    }
}
