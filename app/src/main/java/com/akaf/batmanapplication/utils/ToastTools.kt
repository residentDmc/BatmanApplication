package com.akaf.batmanapplication.utils

import android.widget.Toast
import com.akaf.batmanapplication.App

class ToastTools {

    companion object{
        private var toastTools: ToastTools? = null

        val instanceToast: ToastTools
            get() {
                if (toastTools == null) toastTools = ToastTools()
                return toastTools!!
            }
    }

    fun toast(message:String){
        Toast.makeText(App.activity,message,Toast.LENGTH_LONG).show()
    }
}