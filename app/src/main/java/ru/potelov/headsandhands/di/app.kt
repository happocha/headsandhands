package ru.potelov.headsandhands.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


import ru.potelov.headsandhands.App
import ru.potelov.headsandhands.BuildConfig
import ru.potelov.headsandhands.data.Repository
import ru.potelov.headsandhands.data.RepositoryImpl
import ru.potelov.headsandhands.data.model.Weather
import ru.potelov.headsandhands.data.remote.RemoteService
import ru.potelov.headsandhands.data.remote.WeatherModelDeserializer
import ru.potelov.headsandhands.utils.APP_ID
import ru.potelov.headsandhands.utils.BASE_URL
import ru.potelov.headsandhands.utils.DATE_FORMAT

fun App.provideRepository(): Repository =
    RepositoryImpl(remoteService)

fun App.provideRemoteService(): RemoteService =
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(
            OkHttpClient.Builder()
                .addQueryParam(APP_ID)
                .setDebugEnabled(BuildConfig.DEBUG)
                .build()
        )
        .build()
        .create(RemoteService::class.java)

fun provideGson(): Gson =
    GsonBuilder()
        .registerTypeAdapter(Weather::class.java, WeatherModelDeserializer())
        .setDateFormat(DATE_FORMAT)
        .create()

private fun OkHttpClient.Builder.setDebugEnabled(debugEnabled: Boolean) =
    apply {
        if (debugEnabled) {
            addNetworkInterceptor(HttpLoggingInterceptor().also {
                it.level = HttpLoggingInterceptor.Level.BODY
            })
        }
    }

private fun OkHttpClient.Builder.addQueryParam(appId: String) =
    apply {
        addInterceptor { chain ->
            chain.proceed(
                chain.request()
                    .newBuilder()
                    .url(chain.request().url().newBuilder().addQueryParameter("APPID", appId).build())
                    .build()
            )
        }

    }

