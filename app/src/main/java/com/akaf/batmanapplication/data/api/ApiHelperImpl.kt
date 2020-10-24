package com.akaf.batmanapplication.data.api

import com.akaf.batmanapplication.data.model.moviedetail.MovieDetail
import com.akaf.batmanapplication.data.model.movielist.MovieList
import retrofit2.Response

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getMovieList(key: String, imdb_id: String): Response<MovieList> =
        apiService.getMovieList(key, imdb_id)

    override suspend fun getMovieDetail(key: String, movieDetail: String): Response<MovieDetail> =
        apiService.getMovieDetail(key,movieDetail)
}