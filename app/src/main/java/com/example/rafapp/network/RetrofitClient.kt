package com.example.rafapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.rafapp.models.WeatherStation
import retrofit2.http.GET

object RetrofitClient {
    private const val BASE_URL = "https://tu-api-base-url.com/"

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

interface WeatherStationApi {
    @GET("weather-stations")
    suspend fun getWeatherStations(): List<WeatherStation>
}

// Crea una instancia de la API
val weatherStationApi: WeatherStationApi = RetrofitClient.retrofit.create(WeatherStationApi::class.java)