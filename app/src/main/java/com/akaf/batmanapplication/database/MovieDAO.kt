package com.akaf.batmanapplication.database

import androidx.room.*
import com.akaf.batmanapplication.data.model.movielist.Movie

@Dao
interface MovieDAO {

    @Query("select * from movie_table")
    suspend fun getAllMovie(): List<Movie>

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertListMovie(listMovie: List<Movie>)

    @Update
    suspend fun updateListMovie(listMovie: List<Movie>)

    @Query("DELETE FROM movie_table")
    suspend fun delete()
}