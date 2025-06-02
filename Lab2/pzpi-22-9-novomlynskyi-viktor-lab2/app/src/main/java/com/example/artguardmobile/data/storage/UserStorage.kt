package com.example.artguardmobile.data.storage

import android.content.Context
import android.content.SharedPreferences
import com.example.artguardmobile.data.model.User
import com.google.gson.Gson

object UserStorage {
    private const val PREF_NAME = "user_prefs"
    private const val KEY_USER = "user_data"
    private lateinit var prefs: SharedPreferences

    fun init(context: Context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun saveUser(user: User) {
        prefs.edit().putString(KEY_USER, Gson().toJson(user)).apply()
    }

    fun getUser(): User? {
        return prefs.getString(KEY_USER, null)?.let {
            Gson().fromJson(it, User::class.java)
        }
    }

    fun clearUser() {
        prefs.edit().remove(KEY_USER).apply()
    }
}