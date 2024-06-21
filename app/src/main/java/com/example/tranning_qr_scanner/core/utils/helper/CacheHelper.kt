package com.example.tranning_qr_scanner.core.utils.helper

import android.content.Context
import android.content.SharedPreferences
import com.example.tranning_qr_scanner.core.utils.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface AppCache {
    fun setValue(
        key: String,
        value: String
    )

    fun readValue(
        key: String,
    ): String?

    fun removeValue(
        key: String
    )

    fun clearAll()
}

class CacheHelper @Inject constructor(
    @ApplicationContext val context: Context,
) : AppCache {

    private val prefs: SharedPreferences = context.getSharedPreferences(
        Constants.SHARED_PREF_NAME,
        Context.MODE_PRIVATE
    )

    override fun setValue(
        key: String,
        value: String
    ) =
        prefs.edit().putString(key, value).apply()


    override fun readValue(
        key: String,
    ): String? =
        prefs.getString(key, null)

    override fun removeValue(key: String) = prefs.edit().remove(key).apply()

    override fun clearAll() = prefs.edit().clear().apply()
}