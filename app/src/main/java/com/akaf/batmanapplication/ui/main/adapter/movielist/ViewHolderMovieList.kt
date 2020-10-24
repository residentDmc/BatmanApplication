package com.akaf.batmanapplication.ui.main.adapter.movielist

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.akaf.batmanapplication.data.model.movielist.Movie
import com.akaf.batmanapplication.interfaces.OnItemTouchListener
import com.akaf.batmanapplication.utils.GlideTools
import kotlinx.android.synthetic.main.item_movie.view.*

class ViewHolderMovieList(itemView : View) :RecyclerView.ViewHolder(itemView) {

    fun bind(movie: Movie, onItemTouchListener: OnItemTouchListener){
        itemView.txtTitle.text=(String.format("%s %s", movie.title, movie.year))
        GlideTools.instanceGlide.displayImageOriginal(itemView.imgPoster,movie.poster)
        itemView.vItemParent.setOnClickListener { onItemTouchListener.onItemTouchListener(movie) }
    }
}