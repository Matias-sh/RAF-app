package com.example.rafapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.rafapp.R
import com.example.rafapp.ui.activities.LoginActivity
import com.example.rafapp.viewmodels.WeatherStationViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private lateinit var viewModel: WeatherStationViewModel
    private lateinit var temperatureTextView: TextView
    private lateinit var humidityTextView: TextView
    private lateinit var maxTemperatureTextView: TextView
    private lateinit var minTemperatureTextView: TextView
    private lateinit var lastCommunicationTextView: TextView
    private lateinit var refreshButton: Button
    private lateinit var logoutButton: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializar ViewModel
        viewModel = ViewModelProvider(this).get(WeatherStationViewModel::class.java)

        // Inicializar vistas
        temperatureTextView = view.findViewById(R.id.temperatureTextView)
        humidityTextView = view.findViewById(R.id.humidityTextView)
        maxTemperatureTextView = view.findViewById(R.id.maxTemperatureTextView)
        minTemperatureTextView = view.findViewById(R.id.minTemperatureTextView)
        lastCommunicationTextView = view.findViewById(R.id.tvLastCommunication)
        refreshButton = view.findViewById(R.id.refreshButton)
        logoutButton = view.findViewById(R.id.btnLogout)

        // Observar los cambios en los datos de las estaciones meteorológicas
        viewModel.weatherStations.observe(viewLifecycleOwner) { stations ->
            if (stations.isNotEmpty()) {
                val latestStation = stations.first()
                val temp = latestStation.sensors.hCAirTemperature
                val humidity = latestStation.sensors.hCRelativeHumidity

                temperatureTextView.text = String.format("%.2f°C", temp?.avg ?: 0.0)
                humidityTextView.text = String.format("Humedad: %.2f%%", humidity?.avg ?: 0.0)
                maxTemperatureTextView.text = String.format("Máx: %.2f°C", temp?.max ?: 0.0)
                minTemperatureTextView.text = String.format("Min: %.2f°C", temp?.min ?: 0.0)

                // Formatear la fecha de última comunicación
                val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                val date = Date(latestStation.date.toLong())
                lastCommunicationTextView.text = "Última comunicación: ${dateFormat.format(date)}"
            }
        }

        // Botón para actualizar los datos
        refreshButton.setOnClickListener {
            viewModel.fetchWeatherStations()
        }

        // Botón para cerrar sesión
        logoutButton.setOnClickListener {
            val sharedPreferences = requireContext().getSharedPreferences("AppPreferences", 0)
            val editor = sharedPreferences.edit()
            editor.putBoolean("isLoggedIn", false)
            editor.apply()

            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        // Cargar datos iniciales
        viewModel.fetchWeatherStations()
    }
}
