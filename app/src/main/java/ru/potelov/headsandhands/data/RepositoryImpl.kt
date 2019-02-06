package ru.potelov.headsandhands.data

import io.reactivex.Single
import io.reactivex.functions.BiFunction
import ru.potelov.headsandhands.data.model.Weather
import ru.potelov.headsandhands.data.model.WeatherDto
import ru.potelov.headsandhands.data.remote.RemoteService

interface Repository : RemoteService {

    fun getWeather(city: String): Single<WeatherDto>
}

class RepositoryImpl constructor(private val remoteService: RemoteService) : Repository {

    override fun getForecastApiCall(city: String): Single<Weather> {
        return remoteService.getForecastApiCall(city)
    }

    override fun getWeatherApiCall(city: String): Single<Weather> {
        return remoteService.getWeatherApiCall(city)
    }

    override fun getWeather(city: String): Single<WeatherDto> {
        return Single.zip(
            getWeatherApiCall(city),
            getForecastApiCall(city),
            BiFunction { weather, forecast  -> WeatherDto(weather, forecast.forecast) })
    }
}