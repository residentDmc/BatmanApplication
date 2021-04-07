package com.akaf.batmanapplication.data.api

interface ApiHelper {

    suspend fun getMovieList(key:String, imdb_id:String): Any
    suspend fun getMovieDetail(key:String, movieDetail:String): Any

}