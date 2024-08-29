package com.example.rafapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rafapp.models.WeatherStation
import com.example.rafapp.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherStationViewModel : ViewModel() {

    private val _weatherStations = MutableLiveData<List<WeatherStation>>()
    val weatherStations: LiveData<List<WeatherStation>> get() = _weatherStations

    init {
        fetchWeatherStations()
    }

    private fun fetchWeatherStations() {
        RetrofitClient.instance.getWeatherStations().enqueue(object : Callback<List<WeatherStation>> {
            override fun onResponse(
                call: Call<List<WeatherStation>>,
                response: Response<List<WeatherStation>>
            ) {
                if (response.isSuccessful) {
                    _weatherStations.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<List<WeatherStation>>, t: Throwable) {
                _weatherStations.postValue(emptyList())
            }
        })
    }
}
