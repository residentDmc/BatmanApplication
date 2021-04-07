package com.akaf.batmanapplication.data.model.moviedetail

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.akaf.batmanapplication.utils.build_config.BuildConfig.Companion.MOVIE_DETAIL_TABLE
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@Entity(tableName = MOVIE_DETAIL_TABLE, indices = [Index(value = ["imdb_id"], unique = true)])
class MovieDetail(

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

    @SerializedName("Rated")
    @ColumnInfo(name = "rated")
    @Expose
    val rated: String,

    @SerializedName("Released")
    @ColumnInfo(name = "released")
    @Expose
    val released: String,

    @SerializedName("Runtime")
    @ColumnInfo(name = "runtime")
    @Expose
    val runtime: String,

    @SerializedName("Genre")
    @ColumnInfo(name = "genre")
    @Expose
    val genre: String,

    @SerializedName("Director")
    @ColumnInfo(name = "director")
    @Expose
    val director: String,

    @SerializedName("Writer")
    @ColumnInfo(name = "writer")
    @Expose
    val writer: String,

    @SerializedName("Actors")
    @ColumnInfo(name = "actors")
    @Expose
    val actors: String,

    @SerializedName("Plot")
    @ColumnInfo(name = "plot")
    @Expose
    val plot: String,

    @SerializedName("Language")
    @ColumnInfo(name = "language")
    @Expose
    val language: String,

    @SerializedName("Country")
    @ColumnInfo(name = "country")
    @Expose
    val country: String,

    @SerializedName("Awards")
    @ColumnInfo(name = "awards")
    @Expose
    val awards: String,

    @SerializedName("Poster")
    @ColumnInfo(name = "poster")
    @Expose
    val poster: String,

    @SerializedName("Ratings")
    @ColumnInfo(name = "ratings")
    @Expose
    val ratings: List<Rating> = ArrayList(),

    @SerializedName("Metascore")
    @ColumnInfo(name = "metascore")
    @Expose
    val metascore: String,

    @SerializedName("imdbRating")
    @ColumnInfo(name = "imdb_rating")
    @Expose
    val imdbRating: String,

    @SerializedName("imdbVotes")
    @ColumnInfo(name = "imdb_votes")
    @Expose
    val imdbVotes: String,

    @SerializedName("imdbID")
    @ColumnInfo(name = "imdb_id")
    @Expose
    val imdbId: String,

    @SerializedName("Type")
    @ColumnInfo(name = "type")
    @Expose
    val type: String,

    @SerializedName("DVD")
    @ColumnInfo(name = "dVD")
    @Expose
    val dVD: String,

    @SerializedName("BoxOffice")
    @ColumnInfo(name = "boxOffice")
    @Expose
    val boxOffice: String,

    @SerializedName("Production")
    @ColumnInfo(name = "production")
    @Expose
    val production: String,

    @SerializedName("Website")
    @ColumnInfo(name = "website")
    @Expose
    val website: String,

    @SerializedName("Response")
    @ColumnInfo(name = "response")
    @Expose
    val response: String,
) {
    val titleAndYear: String
        get() = "$title $year"
}