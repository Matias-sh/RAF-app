package com.example.rafapp.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import WeatherStation

interface WeatherStationService {
    @GET("stationdata/")
    fun getWeatherStations(): Call<List<WeatherStation>>
}

object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:3000/api/"

    val instance: WeatherStationService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(WeatherStationService::class.java)
    }
}
