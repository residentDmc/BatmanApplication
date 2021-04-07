package com.akaf.batmanapplication.data.api

import com.akaf.batmanapplication.data.model.moviedetail.MovieDetail
import com.akaf.batmanapplication.data.model.movielist.MovieList
import com.akaf.batmanapplication.utils.build_config.BuildConfig.Companion.API_KEY_PARAM
import com.akaf.batmanapplication.utils.build_config.BuildConfig.Companion.MOVIE_DETAIL_PARAM
import com.akaf.batmanapplication.utils.build_config.BuildConfig.Companion.MOVIE_LIST_PARAM
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(".")
    suspend fun getMovieList(
        @Query(API_KEY_PARAM) kay: String,
        @Query(MOVIE_LIST_PARAM) movieList: String
    ): MovieList

    @GET(".")
    suspend fun getMovieDetail(
        @Query(API_KEY_PARAM) kay: String,
        @Query(MOVIE_DETAIL_PARAM) movieDetail: String
    ): MovieDetail
}