package com.akaf.batmanapplication.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akaf.batmanapplication.data.model.movielist.MovieList
import com.akaf.batmanapplication.data.repository.MovieListRepository
import com.akaf.kotlinkoin.utils.NetworkHelper
import com.akaf.kotlinkoin.utils.Resource
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val movieListRepository: MovieListRepository,
    private val networkHelper: NetworkHelper,
) : ViewModel() {

    private val movieListMutableLiveData = MutableLiveData<Resource<MovieList>>()

    fun fetchMovieList(key: String, imdb_id: String):LiveData<Resource<MovieList>> {
        viewModelScope.launch {
            movieListMutableLiveData.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                movieListRepository.getMovieList(key, imdb_id).let {
                    if (it.isSuccessful) movieListMutableLiveData.postValue(Resource.success(it.body()))
                }
            } else movieListMutableLiveData.postValue(
                Resource.error(
                    "No internet connection",
                    null
                )
            )
        }
        return movieListMutableLiveData
    }

}