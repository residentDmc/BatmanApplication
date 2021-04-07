package com.akaf.batmanapplication.data.model.movielist

import androidx.room.Entity
import com.akaf.batmanapplication.utils.build_config.BuildConfig.Companion.MOVIE_TABLE
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.ArrayList


class MovieList {

    @SerializedName("Search")
    @Expose
    val movies: ArrayList<Movie> = ArrayList()

    @SerializedName("totalResults")
    @Expose
    val totalResults: String = ""

    @SerializedName("Response")
    @Expose
    val response: String = ""
}