package com.akaf.batmanapplication.ui.main.adapter.genreList

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.genre_item.view.*

class ViewHolderGenre(itemView : View) : RecyclerView.ViewHolder(itemView) {
    fun bind(title: String){
        itemView.txt_genre.text=(title)
    }
}