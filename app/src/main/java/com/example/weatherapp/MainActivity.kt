package com.example.weatherapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var cityNameTextView: TextView
    private lateinit var temperatureTextView: TextView
    private lateinit var weatherDescriptionTextView: TextView
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

        // Starting the views
        cityNameTextView = findViewById(R.id.cityNameTextView)
        temperatureTextView = findViewById(R.id.temperatureTextView)
        weatherDescriptionTextView = findViewById(R.id.weatherDescriptionTextView)
        refreshButton = findViewById(R.id.refreshButton)

        // Setting the listener of the button
        refreshButton.setOnClickListener {
            // Updating the data
            updateWeatherData()
        }

        // Load the information about the weather
        updateWeatherData()

    }

    private fun updateWeatherData() {
        // Updating the data
        cityNameTextView.text = "Balneário Gaivota"
        temperatureTextView.text = "15°C"
        weatherDescriptionTextView.text = "Nublado"
    }
}