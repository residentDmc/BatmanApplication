package com.akaf.batmanapplication.utils.tools

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.akaf.batmanapplication.R
import com.akaf.batmanapplication.utils.application.App.Companion.activity


@Suppress("DEPRECATION")
class ToastTools {

    fun toast(message: String) {
        val layout: View = activity.layoutInflater.inflate(
            R.layout.toast_layout,
            activity.findViewById<View>(R.id.custom_toast_layout_id) as ViewGroup?)
        val text = layout.findViewById<View>(R.id.text) as TextView
        text.setTextColor(Color.WHITE)
        text.text = message
        val lytCard = layout.findViewById<View>(R.id.lyt_card) as CardView
        lytCard.setCardBackgroundColor(activity.resources.getColor(R.color.colorBlack))

        val toast = Toast(activity)
        toast.duration = Toast.LENGTH_LONG
        toast.view = layout
        toast.show()
    }
}