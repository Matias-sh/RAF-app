package com.example.rafapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rafapp.R
import com.example.rafapp.models.WeatherStation

class WeatherStationAdapter : RecyclerView.Adapter<WeatherStationAdapter.ViewHolder>() {

    private var weatherStations: List<WeatherStation> = listOf()

    fun setWeatherStations(stations: List<WeatherStation>) {
        this.weatherStations = stations
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_weather_station, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val station = weatherStations[position]
        holder.bind(station)
    }

    override fun getItemCount(): Int = weatherStations.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val stationNameTextView: TextView = itemView.findViewById(R.id.tvStationName)
        private val temperatureTextView: TextView = itemView.findViewById(R.id.tvTemperature)

        fun bind(station: WeatherStation) {
            // Ejemplo de cómo enlazar datos
            stationNameTextView.text = station.stationID
            temperatureTextView.text = "${station.sensors.hCAirTemperature?.avg} °C"
        }
    }
}
