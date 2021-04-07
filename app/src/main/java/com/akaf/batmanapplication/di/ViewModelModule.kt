package com.akaf.batmanapplication.di

import com.akaf.batmanapplication.ui.main.viewmodel.MovieDetailViewModel
import com.akaf.batmanapplication.ui.main.viewmodel.MovieListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MovieListViewModel(get(),get()) }
    viewModel { MovieDetailViewModel(get(),get()) }
}