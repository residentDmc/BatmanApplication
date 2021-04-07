package com.akaf.batmanapplication.di

import android.content.Context
import android.os.Handler
import androidx.navigation.Navigation
import com.akaf.batmanapplication.BuildConfig.DEBUG
import com.akaf.batmanapplication.R
import com.akaf.batmanapplication.data.api.ApiHelper
import com.akaf.batmanapplication.data.api.ApiHelperImpl
import com.akaf.batmanapplication.data.api.ApiService
import com.akaf.batmanapplication.utils.application.App.Companion.activity
import com.akaf.batmanapplication.utils.type_converters.ToStringConverterFactory
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.akaf.batmanapplication.utils.build_config.BuildConfig.Companion.BASE_URL
import com.akaf.batmanapplication.utils.manager.GridLayoutCountManager
import com.vesam.quiz.utils.manager.KeyboardManager
import com.akaf.batmanapplication.utils.network_helper.NetworkHelper
import com.akaf.batmanapplication.utils.tools.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single { initNavController() }
    single { provideRetrofit(get(),get()) }
    single { provideNetworkHelper(androidContext()) }
    single<ApiHelper> { return@single ApiHelperImpl(get()) }
    single { return@single ThrowableTools(get(),get()) }
    single { return@single Gson() }
    single { return@single Handler() }
    single { return@single ToastTools() }
    single { return@single GlideTools(get(), get()) }
    single { return@single HandelErrorTools() }
    single { return@single ToStringConverterFactory() }
    single { return@single KeyboardManager() }
    single { return@single NetworkTools() }
    single { return@single SplitterTools() }
    single { return@single GridLayoutCountManager(get()) }
    single { return@single BottomSheetDialog(activity) }
    single { provideOkHttpClient() }
}

private fun initNavController() =
        Navigation.findNavController(activity, R.id.my_nav_fragment)

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

fun provideRetrofit(okHttpClient: OkHttpClient, toStringConverterFactory: ToStringConverterFactory): ApiService =
    Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(toStringConverterFactory)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()
        .create(ApiService::class.java)
