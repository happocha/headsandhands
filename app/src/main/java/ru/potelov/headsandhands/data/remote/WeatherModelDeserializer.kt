package ru.potelov.headsandhands.data.remote

import com.google.gson.*
import ru.potelov.headsandhands.data.model.Weather
import java.lang.reflect.Type

class WeatherModelDeserializer : JsonDeserializer<Weather> {

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Weather? {
            val jsonObject = json.asJsonObject
            if (jsonObject.get("list") != null) {
                var forecast = listOf<Weather>()
                val items = jsonObject.get("list").asJsonArray
                items.forEachIndexed { _, jsonElement ->
                    forecast += createWeatherObject(jsonElement.asJsonObject)
                }
                return Weather(forecast)
            }
        return createWeatherObject(jsonObject)
    }

    private fun createWeatherObject(jsonObject: JsonObject): Weather {
        return Weather(
            jsonObject.get("weather").asJsonArray.get(0).asJsonObject.get("description").asString,
            jsonObject.get("dt").asLong,
            jsonObject.get("main").asJsonObject.get("temp").asFloat,
            jsonObject.get("wind").asJsonObject.get("speed").asFloat
        )
    }
}