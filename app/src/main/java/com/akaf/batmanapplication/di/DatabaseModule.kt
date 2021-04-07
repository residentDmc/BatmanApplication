package com.akaf.batmanapplication.di

import com.akaf.batmanapplication.database.MovieDatabase
import org.koin.dsl.module


val databaseModule = module {
    single { MovieDatabase.getInstance(get()) }
}