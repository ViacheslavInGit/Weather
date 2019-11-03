package com.bilyi.viacheslav.weather.data.gson

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import javax.inject.Inject


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

        val tempMin = mainJsonObject.get("temp_min").asDouble
        val tempMax = mainJsonObject.get("temp_max").asDouble

        return WeatherResult(
            tempMin,
            tempMax,
            description
        )
    }
}