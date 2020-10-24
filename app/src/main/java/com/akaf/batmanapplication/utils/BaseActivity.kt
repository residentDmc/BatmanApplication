package com.akaf.batmanapplication.utils

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.akaf.batmanapplication.App

open class BaseActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.activity=this
    }
}