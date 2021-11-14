package com.aridev.cordero.twitdeas.data.preferenceRepository

import com.aridev.cordero.twitdeas.data.model.app.Idea
import com.aridev.cordero.twitdeas.data.model.app.ListIdeas

class PreferenceRepository {
    private val sharedPrefService = PreferenceService()

    fun saveIdea(ideas : ListIdeas) {
        sharedPrefService.setObject(PreferencesKey.IDEA,ideas)
    }

    fun removeIdea(idea : Idea) {

    }

    fun getIdeas() : ListIdeas? {
        return sharedPrefService.getObject(PreferencesKey.IDEA, ListIdeas::class.java)
    }
}