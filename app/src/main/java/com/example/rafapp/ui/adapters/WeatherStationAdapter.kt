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
            // Asigna los valores a los TextViews usando los IDs del XML
            binding.tvStationName.text = weatherStation.name.original ?: "N/A" // Ajusta según tu modelo
            binding.tvTemperature.text = weatherStation.meta?.airTemp?.toString() ?: "N/A"
            // Asegúrate de que estos IDs coincidan con los del layout XML
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<WeatherStation>() {
            override fun areItemsTheSame(oldItem: WeatherStation, newItem: WeatherStation): Boolean {
                return oldItem.info.id == newItem.info.id // Asegúrate de usar el identificador correcto
            }

            override fun areContentsTheSame(oldItem: WeatherStation, newItem: WeatherStation): Boolean {
                return oldItem == newItem
            }
        }
    }
}
