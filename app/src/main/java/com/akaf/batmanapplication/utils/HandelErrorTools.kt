package com.akaf.batmanapplication.utils

import android.widget.Toast
import com.akaf.batmanapplication.App

class HandelErrorTools {

    companion object{
        private var handelErrorTools: HandelErrorTools? = null

        val instance: HandelErrorTools
            get() {
                if (handelErrorTools == null) handelErrorTools = HandelErrorTools()
                return handelErrorTools!!
            }
    }

    fun handelError(e: Exception) =Toast.makeText(App.context, e.message, Toast.LENGTH_SHORT).show()
}