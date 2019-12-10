package com.bilyi.viacheslav.weather.data.weather.gson

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import javax.inject.Inject

// Deserializer, который парсит json-ответ от openweathermap.org в доменную сущность
// Юзаеться в билдере ретрофита в di
class ForecastWeatherResultDeserializer @Inject constructor() :
    JsonDeserializer<ForecastWeatherResult> {

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): ForecastWeatherResult {

        val forecastDayResultList = mutableListOf<WeatherResult>()

        val root = json.asJsonObject

        val list = root.get("list").asJsonArray

        for (item in list) {

            val main = item.asJsonObject.get("main").asJsonObject

            val temp = main.get("temp").asDouble
            val tempMin = main.get("temp_min").asDouble
            val tempMax = main.get("temp_max").asDouble

            val weather = item.asJsonObject.get("weather").asJsonArray[0].asJsonObject

            val description = weather.get("description").asString

            forecastDayResultList += WeatherResult(
                temp = temp,
                tempMin = tempMin,
                tempMax = tempMax,
                description = description
            )
        }

        return ForecastWeatherResult(
            forecastDayResultList = forecastDayResultList
        )
    }

}