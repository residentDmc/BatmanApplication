package com.akaf.batmanapplication.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akaf.batmanapplication.data.model.movielist.MovieList
import com.akaf.batmanapplication.data.repository.api.MovieListApiRepository
import com.akaf.batmanapplication.data.repository.db.MovieListDbRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieListViewModel(
    private val movieListApiRepository: MovieListApiRepository,
    private val movieListDbRepository: MovieListDbRepository
) : ViewModel() {


    fun fetchMovieList(key: String, imdb_id: String):LiveData<Any> {
        val liveData = MutableLiveData<Any>()
        viewModelScope.launch {
            val allMovie=movieListDbRepository.getAllMovie()
            when {
                allMovie.isEmpty() -> initRequestMovie(key,imdb_id,liveData)
                else -> liveData.postValue(allMovie)
            }
        }
        return liveData
    }

    private suspend fun initRequestMovie(
        key: String,
        imdb_id: String,
        liveData: MutableLiveData<Any>
    ) {
        withContext(Dispatchers.IO) {
            movieListApiRepository.getMovieList(key, imdb_id) }.let{
            when (it) {
                is MovieList -> {
                    movieListDbRepository.insertMovie(it.movies)
                    liveData.postValue(it.movies)
                }
                else -> liveData.postValue(it)
            }
        }
    }

}