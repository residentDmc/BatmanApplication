package com.akaf.batmanapplication.utils.converter

import androidx.room.TypeConverter
import com.akaf.batmanapplication.data.model.moviedetail.Rating
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class ListRatingConverter {
    companion object {
        var gson = Gson()
        @TypeConverter
        @JvmStatic
        fun fromRating(data: String?): List<Rating>?
        {
            if (data == null)
            {
                return Collections.emptyList()
            }

            val listType = object : TypeToken<List<Rating>>() {}.type
            return gson.fromJson(data, listType)
        }

        @TypeConverter
        @JvmStatic
        fun ratingListToString(someObjects: List<Rating>?): String?
        {
            return gson.toJson(someObjects)
        }
    }
}