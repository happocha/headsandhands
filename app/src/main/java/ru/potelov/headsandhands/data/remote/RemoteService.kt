package ru.potelov.headsandhands.data.remote

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.potelov.headsandhands.data.model.Weather

interface RemoteService {

    @GET("forecast")
    fun getForecastApiCall(@Query("q") city: String): Single<Weather>

    @GET("weather")
    fun getWeatherApiCall(@Query("q") city: String): Single<Weather>
}