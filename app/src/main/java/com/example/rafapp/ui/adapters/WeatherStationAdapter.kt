package com.example.rafapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rafapp.databinding.ItemWeatherStationBinding
import WeatherStation

class WeatherStationAdapter(
    private var weatherStations: List<WeatherStation>
) : RecyclerView.Adapter<WeatherStationAdapter.WeatherStationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherStationViewHolder {
        val binding = ItemWeatherStationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherStationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherStationViewHolder, position: Int) {
        holder.bind(weatherStations[position])
    }

    override fun getItemCount(): Int = weatherStations.size

    class WeatherStationViewHolder(private val binding: ItemWeatherStationBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(weatherStation: WeatherStation) {
            binding.tvStationName.text = weatherStation.stationID
            binding.tvTemperature.text = "${weatherStation.sensors.solarRadiation?.avg}°C"
        }
    }

    fun updateData(newWeatherStations: List<WeatherStation>) {
        weatherStations = newWeatherStations
        notifyDataSetChanged()
    }
}
