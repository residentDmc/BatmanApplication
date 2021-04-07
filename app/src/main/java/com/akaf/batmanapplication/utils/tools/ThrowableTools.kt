package com.akaf.batmanapplication.utils.tools

import com.akaf.batmanapplication.R
import com.akaf.batmanapplication.utils.application.App.Companion.context
import com.google.gson.Gson
import retrofit2.HttpException
import java.net.SocketTimeoutException


class ThrowableTools(private val networkTools: NetworkTools, private val gson: Gson) {

    fun getThrowableError(throwable: Throwable): String {
        return initResultException(throwable)
    }

    private fun initResultException(throwable: Throwable): String {
        return when {
            networkTools.isNetworkAvailable -> context.getString(R.string.no_connection)
            throwable is HttpException -> initHttpException(throwable)
            throwable is SocketTimeoutException -> context.getString(R.string.time_out)
            else -> throwable.message.toString()
        }
    }

    private fun initHttpException(throwable: Throwable): String {
        return throwable.message!!
    }
}