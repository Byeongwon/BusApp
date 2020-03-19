package com.example.busapp.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.busapp.ui.favorite.model.BusStop
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object FavoriteDataManager {

    private const val PREFERENCE_NAME = "favorite"
    private const val FAVORITE_PREFERENCE_KEY = "favorite_data"

    fun updateFavoriteData(context: Context, busStop: BusStop): Boolean {
        val gson = Gson()
        val test = getFavoriteData(context).toMutableList()

        if (test.contains(busStop)) {
            return false
        }
        test.add(busStop)
        getSharedPreferences(context).edit().putString(FAVORITE_PREFERENCE_KEY, gson.toJson(test))
            .apply()

        return true
    }

    fun getFavoriteData(context: Context): List<BusStop> {
        val gson = Gson()
        val preference = getSharedPreferences(context)

        if (preference.getString(FAVORITE_PREFERENCE_KEY, "") == null) {
            val preference = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
            val test: List<BusStop> = emptyList()
            preference.edit().putString(FAVORITE_PREFERENCE_KEY, gson.toJson(test)).apply()
        }

        val favoriteTag = preference.getString(FAVORITE_PREFERENCE_KEY, "")
        val listType = object : TypeToken<List<BusStop>>() {}.type
        return gson.fromJson<List<BusStop>>(favoriteTag, listType) ?: emptyList()
    }

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    }
}