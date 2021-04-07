package com.akaf.batmanapplication.utils.tools

import android.util.Log


class HandelErrorTools {

    fun handelError(e: Exception) {
        Log.d("TAG", "handelError Exception: $e")
    }

    fun handelError(t: Throwable) {
        Log.d("TAG", "handelError Throwable: $t")
    }
}