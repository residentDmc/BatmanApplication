package com.akaf.batmanapplication.utils

import android.widget.ImageView
import com.akaf.batmanapplication.App
import com.akaf.batmanapplication.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class GlideTools {

    companion object{
        private var glideTools: GlideTools? = null

        val instanceGlide: GlideTools
            get() {
                if (glideTools == null) glideTools = GlideTools()
                return glideTools!!
            }
    }

    fun displayImageOriginal(img: ImageView?, url: String?) {
        try {
            val options: RequestOptions = RequestOptions()
                .placeholder(R.drawable.ic_load_pic)
            img?.let {
                Glide.with(App.context).load(url)
                    .apply(options)
                    .into(it)
            }
        } catch (e: Exception) {
            HandelErrorTools.instance.handelError(e)
        }
    }
}