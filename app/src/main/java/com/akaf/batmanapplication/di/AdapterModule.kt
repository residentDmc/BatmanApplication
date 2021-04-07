package com.akaf.batmanapplication.di

import com.akaf.batmanapplication.ui.main.adapter.genreList.AdapterGenre
import com.akaf.batmanapplication.ui.main.adapter.movielist.AdapterMovieList
import com.akaf.batmanapplication.ui.main.adapter.ratingList.AdapterRating
import org.koin.dsl.module

val adapterModule = module {
    single { AdapterMovieList(get(),get()) }
    single { AdapterGenre() }
    single { AdapterRating() }
}