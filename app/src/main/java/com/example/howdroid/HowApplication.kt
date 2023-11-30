package com.example.howdroid

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.howdroid.data.datasource.local.HowDroidStorage
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class HowApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        HowDroidStorage.init(this)
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}
