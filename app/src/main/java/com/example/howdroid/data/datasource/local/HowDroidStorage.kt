package com.example.howdroid.data.datasource.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.howdroid.BuildConfig
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HowDroidStorage @Inject constructor(@ApplicationContext context: Context) {
    private val masterKey = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val pref: SharedPreferences =
        if (BuildConfig.DEBUG) {
            context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        } else {
            EncryptedSharedPreferences.create(
                context,
                FILE_NAME,
                masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
            )
        }
    var accessToken: String
        set(value) = pref.edit { putString(ACCESS_TOKEN, value) }
        get() = pref.getString(
            ACCESS_TOKEN,
            "",
        ) ?: ""

    var isLogin: Boolean
        set(value) = pref.edit { putBoolean(IS_LOGIN, value) }
        get() = pref.getBoolean(IS_LOGIN, false)

    fun clear() {
        pref.edit {
            clear()
        }
    }
}

const val FILE_NAME = "HdDataStore"
const val ACCESS_TOKEN = "accessToken"
const val IS_LOGIN = "IsLogin"
