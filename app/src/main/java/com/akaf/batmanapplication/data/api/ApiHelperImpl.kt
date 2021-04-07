package com.akaf.batmanapplication.data.api

import com.akaf.batmanapplication.data.model.moviedetail.MovieDetail
import com.akaf.batmanapplication.data.model.movielist.MovieList
import retrofit2.Response

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getMovieList(key: String, imdb_id: String) =
            try {
                apiService.getMovieList(key, imdb_id)
            } catch (e: Exception) {
                e
            }

    override suspend fun getMovieDetail(key: String, movieDetail: String) =
            try {
                apiService.getMovieDetail(key, movieDetail)
            } catch (e: Exception) {
                e
            }
}