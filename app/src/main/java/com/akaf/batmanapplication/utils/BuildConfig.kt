package com.akaf.batmanapplication.utils


class BuildConfig {
    companion object{

        const val BASE_URL = "http://www.omdbapi.com/"

        // param
        const val API_KEY_PARAM = "apikey"
        const val MOVIE_LIST_PARAM = "s"
        const val MOVIE_DETAIL_PARAM = "i"

        // param_key
        const val API_KEY = "3e974fca"
        const val MOVIE_LIST = "batman"

        // bundle
        const val ERROR_BUNDLE = "error"
        const val ITEM_ID_BUNDLE = "item_id"

        // table
        const val MOVIE_TABLE = "movie_table"
        const val MOVIE_DETAIL_TABLE = "movie_detail_table"
        const val MOVIE_DETAIL_RATING_TABLE = "movie_detail_rating_table"


        // database
        const val MOVIE = "movie_database"
    }
}