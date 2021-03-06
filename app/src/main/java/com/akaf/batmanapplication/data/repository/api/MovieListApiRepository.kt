package com.akaf.batmanapplication.data.repository.api

import com.akaf.batmanapplication.data.api.ApiHelper

class MovieListApiRepository(private val apiHelper: ApiHelper) {

    suspend fun getMovieList(key:String, imdb_id:String) =apiHelper.getMovieList(key,imdb_id)
}