package com.akaf.batmanapplication.data.repository.api

import com.akaf.batmanapplication.data.api.ApiHelper

class MovieDetailRepository(private val apiHelper: ApiHelper) {

    suspend fun getMovieDetail(key:String, imdb_id:String) =apiHelper.getMovieDetail(key,imdb_id)
}