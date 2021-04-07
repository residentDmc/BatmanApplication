package com.akaf.batmanapplication.data.model.movielist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.akaf.batmanapplication.utils.build_config.BuildConfig.Companion.MOVIE_TABLE
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@Entity(tableName = MOVIE_TABLE)
data class Movie(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,
    @SerializedName("Title")
    @ColumnInfo(name = "title")
    @Expose
    val title: String,

    @SerializedName("Year")
    @ColumnInfo(name = "year")
    @Expose
    val year: String,

    @SerializedName("imdbID")
    @ColumnInfo(name = "imdb_iD")
    @Expose
    val imdbID: String,

    @SerializedName("Type")
    @ColumnInfo(name = "type")
    @Expose
    val type: String,

    @SerializedName("Poster")
    @ColumnInfo(name = "poster")
    @Expose
    val poster: String,
)