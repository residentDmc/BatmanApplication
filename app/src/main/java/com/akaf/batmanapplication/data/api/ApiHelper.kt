package com.akaf.batmanapplication.data.api

import com.akaf.batmanapplication.data.model.moviedetail.MovieDetail
import com.akaf.batmanapplication.data.model.movielist.MovieList
import retrofit2.Response

interface ApiHelper {

    suspend fun getMovieList(key:String, imdb_id:String): Response<MovieList>
    suspend fun getMovieDetail(key:String, movieDetail:String): Response<MovieDetail>

}