package com.akaf.batmanapplication.data.model.moviedetail

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.akaf.batmanapplication.utils.build_config.BuildConfig.Companion.MOVIE_DETAIL_RATING_TABLE
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@Entity(tableName = MOVIE_DETAIL_RATING_TABLE)
class Rating {

    @ColumnInfo(name = "id")
    @PrimaryKey
    private val id = 0

    @SerializedName("Source")
    @Expose
    val source: String = ""

    @SerializedName("Value")
    @Expose
    val value: String = ""
}