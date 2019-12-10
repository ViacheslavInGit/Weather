package com.bilyi.viacheslav.weather.data.weather.gson

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import javax.inject.Inject

// Deserializer, который парсит json-ответ от openweathermap.org в доменную сущность
// Юзаеться в билдере ретрофита в di
class WeatherResultDeserializer @Inject constructor() : JsonDeserializer<WeatherResult> {

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): WeatherResult {

        val root = json.asJsonObject

        val weatherJsonObject = root
            .get("weather")
            .asJsonArray
            .get(0)
            .asJsonObject

        val description = weatherJsonObject.get("description").asString

        val mainJsonObject = root
            .get("main")
            .asJsonObject

        val temp = mainJsonObject.get("temp").asDouble
        val tempMin = mainJsonObject.get("temp_min").asDouble
        val tempMax = mainJsonObject.get("temp_max").asDouble

        return WeatherResult(
            temp,
            tempMin,
            tempMax,
            description
        )
    }
}