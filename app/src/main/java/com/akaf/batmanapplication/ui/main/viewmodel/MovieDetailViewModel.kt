package com.akaf.batmanapplication.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akaf.batmanapplication.data.model.moviedetail.MovieDetail
import com.akaf.batmanapplication.data.repository.MovieDetailRepository
import com.akaf.kotlinkoin.utils.NetworkHelper
import com.akaf.kotlinkoin.utils.Resource
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val movieDetailRepository: MovieDetailRepository,
    private val networkHelper: NetworkHelper,
) : ViewModel() {

    private val movieListMutableLiveData = MutableLiveData<Resource<MovieDetail>>()

    fun fetchMovieDetail(key: String, imdb_id: String):LiveData<Resource<MovieDetail>> {
        viewModelScope.launch {
            movieListMutableLiveData.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                movieDetailRepository.getMovieDetail(key, imdb_id).let {
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