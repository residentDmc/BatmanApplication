package com.akaf.batmanapplication.interfaces

import com.akaf.batmanapplication.data.model.movielist.Movie

interface OnItemTouchListener {
    fun onItemTouchListener(movie: Movie)
}