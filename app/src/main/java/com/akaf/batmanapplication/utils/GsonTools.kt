package com.akaf.batmanapplication.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder

class GsonTools {

    companion object{
        private var gsonTools: Gson? = null
        private var gsonExposeAnnotationTools: Gson? = null

        val instanceGson: Gson
            get() {
                if (gsonTools == null) gsonTools = Gson()
                return gsonTools!!
            }

        val instanceGsonExposeAnnotationTools: Gson
            get() {
                if (gsonExposeAnnotationTools == null) gsonExposeAnnotationTools = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
                return gsonExposeAnnotationTools!!
            }
    }
}