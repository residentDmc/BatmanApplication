package com.akaf.batmanapplication.utils.manager

import android.content.Context
import android.content.res.Configuration

@Suppress("DEPRECATED_IDENTITY_EQUALS")
class GridLayoutCountManager(private val context: Context) {

    fun getColumnWidth(): Int = when {
        context.resources.configuration.screenLayout and
                Configuration.SCREENLAYOUT_SIZE_MASK ===
                Configuration.SCREENLAYOUT_SIZE_XLARGE -> 5
        context.resources.configuration.screenLayout and
                Configuration.SCREENLAYOUT_SIZE_MASK ===
                Configuration.SCREENLAYOUT_SIZE_LARGE -> 3
        context.resources.configuration.screenLayout and
                Configuration.SCREENLAYOUT_SIZE_MASK ===
                Configuration.SCREENLAYOUT_SIZE_NORMAL -> 2
        else -> 5
    }

}