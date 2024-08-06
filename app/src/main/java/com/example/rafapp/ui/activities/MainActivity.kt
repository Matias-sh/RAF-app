package com.example.rafapp.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.rafapp.R
import com.example.rafapp.viewmodels.WeatherStationViewModel
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var temperatureTextView: TextView
    private lateinit var humidityTextView: TextView
    private lateinit var maxTemperatureTextView: TextView
    private lateinit var minTemperatureTextView: TextView
    private lateinit var lastCommunicationTextView: TextView
    private lateinit var refreshButton: Button
    private lateinit var logoutButton: Button
    private lateinit var viewModel: WeatherStationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Verificar estado de sesión
        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

        if (!isLoggedIn) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
            return
        }

        // Inicializar ViewModel
        viewModel = ViewModelProvider(this).get(WeatherStationViewModel::class.java)

        // Inicializar vistas
        temperatureTextView = findViewById(R.id.temperatureTextView)
        humidityTextView = findViewById(R.id.humidityTextView)
        maxTemperatureTextView = findViewById(R.id.maxTemperatureTextView)
        minTemperatureTextView = findViewById(R.id.minTemperatureTextView)
        lastCommunicationTextView = findViewById(R.id.tvLastCommunication)
        refreshButton = findViewById(R.id.refreshButton)
        logoutButton = findViewById(R.id.btnLoggout)

        // Observar los cambios en los datos de las estaciones meteorológicas
        viewModel.weatherStations.observe(this) { stations ->
            if (stations.isNotEmpty()) {
                val latestStation = stations.first()
                val temp = latestStation.sensors.hCAirTemperature
                val humidity = latestStation.sensors.hCRelativeHumidity

                temperatureTextView.text = String.format("%.2f°C", temp.avg)
                humidityTextView.text = String.format("Humedad: %.2f%%", humidity.avg)
                maxTemperatureTextView.text = String.format("Máx: %.2f°", temp.max)
                minTemperatureTextView.text = String.format("Min: %.2f°", temp.min)

                // Formatear la fecha de última comunicación
                val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                val date = Date(latestStation.date.date.toLong())
                lastCommunicationTextView.text = "Última comunicación: ${dateFormat.format(date)}"
            }
        }

        refreshButton.setOnClickListener {
            viewModel.fetchWeatherStations()
        }

        logoutButton.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.putBoolean("isLoggedIn", false)
            editor.apply()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Cargar datos iniciales
        viewModel.fetchWeatherStations()
    }
}