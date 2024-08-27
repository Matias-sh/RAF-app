package com.example.rafapp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import WeatherStation
import com.example.rafapp.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherStationViewModel : ViewModel() {

    private val _weatherStations = MutableLiveData<List<WeatherStation>?>()
    val weatherStations: MutableLiveData<List<WeatherStation>?> get() = _weatherStations

    init {
        fetchWeatherStations()
    }

    private fun fetchWeatherStations() {
        RetrofitClient.weatherStationService.getWeatherStations().enqueue(object : Callback<List<WeatherStation>> {
            override fun onResponse(call: Call<List<WeatherStation>>, response: Response<List<WeatherStation>>) {
                if (response.isSuccessful) {
                    Log.d("WeatherStationViewModel", "Response received: ${response.body()}")
                    _weatherStations.value = response.body()
                } else {
                    Log.e("WeatherStationViewModel", "Error in response: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<WeatherStation>>, t: Throwable) {
                Log.e("WeatherStationViewModel", "API call failed: ${t.message}")
            }
        })
    }
}

