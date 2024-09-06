package com.example.rafapp.viewmodel

import android.util.Log
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

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun fetchWeatherStations() {
        val service = RetrofitClient.instance

        service.getWeatherStations().enqueue(object : Callback<List<WeatherStation>> {
            override fun onResponse(
                call: Call<List<WeatherStation>>,
                response: Response<List<WeatherStation>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    _weatherStations.postValue(response.body())
                } else {
                    _error.postValue("Error: ${response.code()} ${response.message()}")
                    Log.e("API_ERROR", "Error: ${response.code()} ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<WeatherStation>>, t: Throwable) {
                _error.postValue("Network error: ${t.localizedMessage}")
                Log.e("NETWORK_ERROR", "Network error: ${t.localizedMessage}")
            }
        })
    }
}

