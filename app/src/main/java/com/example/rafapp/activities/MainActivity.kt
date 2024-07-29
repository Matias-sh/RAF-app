package com.example.rafapp.activities

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.rafapp.R

class MainActivity : AppCompatActivity() {

    private lateinit var temperatureTextView: TextView
    private lateinit var humidityTextView: TextView
    private lateinit var refreshButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        temperatureTextView = findViewById(R.id.temperatureTextView)
        //humidityTextView = findViewById(R.id.humidityTextView)
        //refreshButton = findViewById(R.id.refreshButton)

        refreshButton.setOnClickListener {
            // Aquí llamaremos a la API cuando la implementemos
            // Por ahora, solo actualizamos el texto
            temperatureTextView.text = "Temperatura: 25°C"
            humidityTextView.text = "Humedad: 60%"
        }
    }
}