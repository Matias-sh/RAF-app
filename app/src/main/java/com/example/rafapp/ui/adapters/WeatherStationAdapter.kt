package com.example.rafapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rafapp.databinding.ItemWeatherStationBinding
import com.example.rafapp.models.WeatherStation

class WeatherStationAdapter :
    ListAdapter<WeatherStation, WeatherStationAdapter.WeatherStationViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherStationViewHolder {
        val binding = ItemWeatherStationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return WeatherStationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherStationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class WeatherStationViewHolder(private val binding: ItemWeatherStationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(weatherStation: WeatherStation) {
            binding.textViewDate.text = weatherStation.date
            binding.textViewStationId.text = weatherStation.stationID ?: "N/A"
            binding.textViewSolarRadiation.text = weatherStation.sensors.solarRadiation?.avg?.toString() ?: "N/A"
            binding.textViewAirTemperature.text = weatherStation.sensors.hCAirTemperature?.avg?.toString() ?: "N/A"
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<WeatherStation>() {
            override fun areItemsTheSame(oldItem: WeatherStation, newItem: WeatherStation): Boolean {
                return oldItem._id == newItem._id
            }

            override fun areContentsTheSame(oldItem: WeatherStation, newItem: WeatherStation): Boolean {
                return oldItem == newItem
            }
        }
    }
}
