package com.akaf.batmanapplication.di

import com.akaf.batmanapplication.data.repository.api.MovieDetailRepository
import com.akaf.batmanapplication.data.repository.api.MovieListApiRepository
import com.akaf.batmanapplication.data.repository.db.MovieDetailDbRepository
import com.akaf.batmanapplication.data.repository.db.MovieListDbRepository
import org.koin.dsl.module

val repoModule = module {
    single { MovieListApiRepository(get()) }
    single { MovieListDbRepository(get()) }
    single { MovieDetailRepository(get()) }
    single { MovieDetailDbRepository(get()) }
}