package com.akaf.batmanapplication.ui.main.adapter.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akaf.batmanapplication.App
import com.akaf.batmanapplication.R
import com.akaf.batmanapplication.data.model.movielist.Movie
import com.akaf.batmanapplication.interfaces.OnItemTouchListener

class AdapterMovieList : RecyclerView.Adapter<ViewHolderMovieList>() {

    private val movieList: ArrayList<Movie> = ArrayList()
    private var onItemTouchListener: OnItemTouchListener? = null

    fun setOnClickListener(onItemTouchListener: OnItemTouchListener) {
        this.onItemTouchListener = onItemTouchListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMovieList =
        ViewHolderMovieList(
            LayoutInflater.from(App.context).inflate(R.layout.item_movie, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolderMovieList, position: Int) =
        holder.bind(movieList[position], onItemTouchListener!!)

    override fun getItemCount(): Int = movieList.size

    fun addData(list: List<Movie>) {
        movieList.addAll(list)
        notifyDataSetChanged()
    }
}