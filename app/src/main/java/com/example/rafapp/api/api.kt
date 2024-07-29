package com.example.rafapp.api

import retrofit2.http.GET
import retrofit2.http.Query

interface MetosApiService {
    @GET("data")
    suspend fun getWeatherData(
        @Query("user_key") userKey: String,
        @Query("station_name") stationName: String
    ): WeatherResponse
}

// Esta clase necesitará ser definida basada en la respuesta real de la API
data class WeatherResponse(
    val temperature: Double,
    val humidity: Double,
    // Agrega más campos según la respuesta de la API
)