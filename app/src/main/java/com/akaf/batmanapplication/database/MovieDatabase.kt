package com.akaf.batmanapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.akaf.batmanapplication.data.model.moviedetail.MovieDetail
import com.akaf.batmanapplication.data.model.movielist.Movie
import com.akaf.batmanapplication.utils.build_config.BuildConfig.Companion.MOVIE_DATABASE
import com.akaf.batmanapplication.utils.converter.ListRatingConverter

@Database(entities = [Movie::class, MovieDetail::class], version = 1, exportSchema = false)
@TypeConverters(ListRatingConverter::class)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDAO(): MovieDAO
    abstract fun movieDetailDao(): MovieDetailDao

    companion object {
        private var instance: MovieDatabase? = null
        fun getInstance(context: Context): MovieDatabase {
            if (instance == null) {

                instance = Room.databaseBuilder(
                        context,
                        MovieDatabase::class.java,
                        MOVIE_DATABASE)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
            }

            return instance!!
        }
    }
}