package com.akaf.batmanapplication

import android.app.Activity
import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.akaf.batmanapplication.di.appModule
import com.akaf.batmanapplication.di.repoModule
import com.akaf.batmanapplication.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : MultiDexApplication() {

    companion object {
        lateinit var context: Context
        lateinit var activity: Activity
    }

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        context = applicationContext
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, repoModule, viewModelModule))
        }
    }
}