package com.aridev.cordero.twitdeas.data.preferenceRepository

import android.content.SharedPreferences
import com.google.gson.Gson
import org.koin.core.KoinComponent
import org.koin.core.inject


enum class PreferencesKey(val value : String) {
    IDEA("idea")
}

class PreferenceService: KoinComponent {
    private val sharedPreferences: SharedPreferences by inject()

    fun setObject(key : PreferencesKey, value : Any) {
        val editor = sharedPreferences.edit()
        editor.putString(key.value, Gson().toJson(value)).apply()
    }

    fun setString(key: PreferencesKey, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key.value, value).apply()
    }

    fun setBoolean(key: PreferencesKey, value : Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(key.value, value).apply()
    }

    fun getString( key: PreferencesKey): String? {
        return sharedPreferences.getString(key.value, null)
    }

    fun getBoolean( key: PreferencesKey) : Boolean? {
        return sharedPreferences.getBoolean(key.value, false)
    }

    fun <T> getObject(key : PreferencesKey, typeObj : Class<T>): T? {
        val data = sharedPreferences.getString(key.value, null)
        return Gson().fromJson(data,typeObj)
    }

    fun remove(key: PreferencesKey) {
        val editor = sharedPreferences.edit()
        editor.remove(key.value).apply()
    }

    // Elimina las SharedPreferences del dominio app
    fun clear() {
        val editor = sharedPreferences.edit()
        editor.clear().apply()
    }
}