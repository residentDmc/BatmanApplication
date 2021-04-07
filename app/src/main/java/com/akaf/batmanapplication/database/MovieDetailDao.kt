package com.akaf.batmanapplication.database

import androidx.room.*
import com.akaf.batmanapplication.data.model.moviedetail.MovieDetail
import com.akaf.batmanapplication.data.model.movielist.Movie

@Dao
interface MovieDetailDao {

    @Query("SELECT * FROM movie_detail_table WHERE imdb_id IN (:imdb_id) ")
    suspend fun getMovieDetail(imdb_id: String): MovieDetail?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovieDetail(movieDetail: MovieDetail)

    @Update
    suspend fun updateMovieDetail(movieDetail: MovieDetail)

    @Query("DELETE FROM movie_table")
    suspend fun delete()
}