package com.akaf.batmanapplication.data.repository.db

import com.akaf.batmanapplication.data.model.moviedetail.MovieDetail
import com.akaf.batmanapplication.data.model.movielist.Movie
import com.akaf.batmanapplication.database.MovieDatabase

class MovieDetailDbRepository(private val MovieDatabase: MovieDatabase) {

    suspend fun getMovieDetail(imdb_id: String) = MovieDatabase.movieDetailDao().getMovieDetail(imdb_id)
    suspend fun insertMovieDetail(movieDetail: MovieDetail) = MovieDatabase.movieDetailDao().insertMovieDetail(movieDetail)
    suspend fun updateMovieDetail(movieDetail: MovieDetail) = MovieDatabase.movieDetailDao().updateMovieDetail(movieDetail)
    suspend fun delete() = MovieDatabase.movieDetailDao().delete()

}