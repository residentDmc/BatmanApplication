package com.akaf.batmanapplication.data.model.movielist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

class MovieList {

    @SerializedName("Search")
    @Expose
    val movies: List<Movie> = ArrayList()

    @SerializedName("totalResults")
    @Expose
    val totalResults: String = ""

    @SerializedName("Response")
    @Expose
    val response: String = ""
}