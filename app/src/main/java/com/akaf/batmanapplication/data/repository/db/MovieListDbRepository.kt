package com.akaf.batmanapplication.data.repository.db

import com.akaf.batmanapplication.data.model.movielist.Movie
import com.akaf.batmanapplication.database.MovieDatabase

class MovieListDbRepository(private val MovieDatabase: MovieDatabase) {

    suspend fun getAllMovie() = MovieDatabase.movieDAO().getAllMovie()
    suspend fun insertMovie(listMovie: List<Movie>) = MovieDatabase.movieDAO().insertListMovie(listMovie)
    suspend fun updateMovie(listMovie: List<Movie>) = MovieDatabase.movieDAO().updateListMovie(listMovie)
    suspend fun delete() = MovieDatabase.movieDAO().delete()

}