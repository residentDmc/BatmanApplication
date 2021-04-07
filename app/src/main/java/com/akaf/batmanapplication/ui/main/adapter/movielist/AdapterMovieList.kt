package com.akaf.batmanapplication.ui.main.adapter.movielist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akaf.batmanapplication.utils.application.App
import com.akaf.batmanapplication.R
import com.akaf.batmanapplication.data.model.movielist.Movie
import com.akaf.batmanapplication.interfaces.OnItemTouchListener
import com.akaf.batmanapplication.utils.tools.GlideTools

class AdapterMovieList(private val context: Context,private val glideTools: GlideTools) : RecyclerView.Adapter<ViewHolderMovieList>() {

    private val movieList: ArrayList<Movie> = ArrayList()
    private var onItemTouchListener: OnItemTouchListener? = null

    fun setOnClickListener(onItemTouchListener: OnItemTouchListener) {
        this.onItemTouchListener = onItemTouchListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMovieList =
        ViewHolderMovieList(
            LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolderMovieList, position: Int) =
        holder.bind(movieList[position],glideTools, onItemTouchListener!!)

    override fun getItemCount(): Int = movieList.size

    fun updateList(list: List<Movie>) {
        movieList.clear()
        movieList.addAll(list)
        notifyDataSetChanged()
    }
}