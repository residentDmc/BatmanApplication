package com.akaf.batmanapplication.di

import android.content.Context
import com.akaf.batmanapplication.BuildConfig.DEBUG
import com.akaf.batmanapplication.data.api.ApiHelper
import com.akaf.batmanapplication.data.api.ApiHelperImpl
import com.akaf.batmanapplication.data.api.ApiService
import com.akaf.batmanapplication.utils.BuildConfig.Companion.BASE_URL
import com.akaf.batmanapplication.utils.GsonTools
import com.akaf.batmanapplication.utils.ToStringConverterFactory
import com.akaf.kotlinkoin.utils.NetworkHelper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get(), BASE_URL) }
    single { provideApiService(get()) }
    single { provideNetworkHelper(androidContext()) }

    single<ApiHelper> {
        return@single ApiHelperImpl(get())
    }
}

fun provideNetworkHelper(context: Context) = NetworkHelper(context)

fun provideOkHttpClient() = if (DEBUG) {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
} else OkHttpClient
    .Builder()
    .build()

fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(ToStringConverterFactory.instance)
        .addConverterFactory(GsonConverterFactory.create(GsonTools.instanceGsonExposeAnnotationTools))
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)