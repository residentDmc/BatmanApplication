package com.akaf.batmanapplication.utils.tools

import android.content.Context
import android.widget.ImageView
import com.akaf.batmanapplication.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class GlideTools(private val context: Context, private val handelErrorTools: HandelErrorTools) {

    fun displayImageOriginal(img: ImageView?, url: String?) {
        try {
            val options: RequestOptions = RequestOptions()
                    .placeholder(R.drawable.ic_load_pic)
            img?.let {
                Glide.with(context).load(url)
                        .apply(options)
                        .into(it)
            }
        } catch (e: Exception) {
            handelErrorTools.handelError(e)
        }
    }
}