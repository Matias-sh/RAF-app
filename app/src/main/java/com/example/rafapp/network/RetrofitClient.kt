package com.example.rafapp.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import com.example.rafapp.models.WeatherStation

interface WeatherStationService {
    @GET("path/to/endpoint")
    fun getWeatherStations(): Call<List<WeatherStation>>
}

object RetrofitClient {
    private const val BASE_URL = "https://your.api.url/" // Reemplaza con la URL de tu API

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val weatherStationService: WeatherStationService by lazy {
        retrofit.create(WeatherStationService::class.java)
    }
}
