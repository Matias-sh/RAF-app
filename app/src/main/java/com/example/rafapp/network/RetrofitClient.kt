package com.example.rafapp.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import WeatherStation

interface WeatherStationService {
    @GET("api/stationdata/")  // Ruta correcta para acceder a los datos
    fun getWeatherStations(): Call<List<WeatherStation>>
}

object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:8000/"  // URL base de tu API local

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val weatherStationService: WeatherStationService by lazy {
        retrofit.create(WeatherStationService::class.java)
    }
}

