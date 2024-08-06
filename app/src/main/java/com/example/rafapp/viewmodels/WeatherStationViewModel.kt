package com.example.rafapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rafapp.models.WeatherStation
import com.example.rafapp.network.weatherStationApi
import kotlinx.coroutines.launch

class WeatherStationViewModel : ViewModel() {
    private val _weatherStations = MutableLiveData<List<WeatherStation>>()
    val weatherStations: LiveData<List<WeatherStation>> = _weatherStations

    fun fetchWeatherStations() {
        viewModelScope.launch {
            try {
                val stations = weatherStationApi.getWeatherStations()
                _weatherStations.value = stations
            } catch (e: Exception) {
                // Manejar el error
                e.printStackTrace() // Para debug, imprime el stack trace
            }
        }
    }
}