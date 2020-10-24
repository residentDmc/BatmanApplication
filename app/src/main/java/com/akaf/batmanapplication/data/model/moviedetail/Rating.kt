package com.akaf.batmanapplication.data.model.moviedetail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Rating {

    @SerializedName("Source")
    @Expose
    val source: String = ""

    @SerializedName("Value")
    @Expose
    val value: String = ""
}