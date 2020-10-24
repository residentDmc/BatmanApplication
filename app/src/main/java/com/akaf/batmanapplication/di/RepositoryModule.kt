package com.akaf.batmanapplication.di

import com.akaf.batmanapplication.data.repository.MovieDetailRepository
import com.akaf.batmanapplication.data.repository.MovieListRepository
import org.koin.dsl.module

val repoModule = module {
    single {
        MovieListRepository(get())
    }

    single {
        MovieDetailRepository(get())
    }
}