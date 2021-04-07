package com.akaf.batmanapplication.utils.application

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.akaf.batmanapplication.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : MultiDexApplication() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        @SuppressLint("StaticFieldLeak")
        lateinit var activity: Activity
    }

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        context = applicationContext
        startKoin {
            androidContext(this@App)
            val modules = modules(
                listOf(
                    appModule,
                    repoModule,
                    viewModelModule,
                    databaseModule,
                    adapterModule
                )
            )
        }
    }
}