package com.akaf.batmanapplication.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akaf.batmanapplication.data.model.moviedetail.MovieDetail
import com.akaf.batmanapplication.data.model.movielist.MovieList
import com.akaf.batmanapplication.data.repository.api.MovieDetailRepository
import com.akaf.batmanapplication.data.repository.db.MovieDetailDbRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailViewModel(
        private val movieDetailRepository: MovieDetailRepository,
        private val movieDetailDbRepository: MovieDetailDbRepository
) : ViewModel() {


    fun getMovieDetail(key: String, imdb_id: String):LiveData<Any> {
        val liveData = MutableLiveData<Any>()
        viewModelScope.launch {
            when (val movieDetail=movieDetailDbRepository.getMovieDetail(imdb_id)){
                null -> initRequestDetailMovie(key,imdb_id,liveData)
                else -> liveData.postValue(movieDetail)
            }
        }
        return liveData
    }

    private suspend fun initRequestDetailMovie(
        key: String,
        imdb_id: String,
        liveData: MutableLiveData<Any>
    ) {
        withContext(Dispatchers.IO) {
            movieDetailRepository.getMovieDetail(key, imdb_id) }.let{
            when (it) {
                is MovieDetail -> {
                    movieDetailDbRepository.insertMovieDetail(it)
                    liveData.postValue(it)
                }
                else -> liveData.postValue(it)
            }
        }
    }

}