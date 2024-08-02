package com.example.rafapp.activities

import android.content.Context
import android.content.Intent
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
    private lateinit var logoutButton: Button

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
            finish() // Cierra MainActivity para que no quede en el stack
            return
        }

        // Inicializar vistas
        temperatureTextView = findViewById(R.id.temperatureTextView)
        humidityTextView = findViewById(R.id.humidityTextView)
        refreshButton = findViewById(R.id.refreshButton)
        logoutButton = findViewById(R.id.btnLoggout)

        refreshButton.setOnClickListener {
            // Aquí llamaremos a la API cuando la implementemos
            // Por ahora, solo actualizamos el texto
            temperatureTextView.text = "Temperatura: 25°C"
            humidityTextView.text = "Humedad: 60%"
        }

        logoutButton.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.putBoolean("isLoggedIn", false)
            editor.apply()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
