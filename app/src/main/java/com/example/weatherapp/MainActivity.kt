package com.example.weatherapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.weatherapp.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var weatherApiService: WeatherApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Setting the view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setting the listener of the system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize Retrofit and WeatherApiService
        setupRetrofit()

        // Setting the listener of the button
        binding.refreshButton.setOnClickListener {
            // Updating the data
            updateWeatherData()
        }

        // Load the information about the weather
        updateWeatherData()
    }

    private fun setupRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        weatherApiService = retrofit.create(WeatherApiService::class.java)
    }

    private fun updateWeatherData() {
        // Updating the data
        lifecycleScope.launch {
            try {
                val response = weatherApiService.getWeather(
                    cityName = "Calgary",
                    apiKey = "338bbb2534975fe61e4723f0d0eeda55"
                )

                binding.cityNameTextView.text = response.name
                binding.temperatureTextView.text = "${response.main.temp}°C"
                binding.weatherDescriptionTextView.text = response.weather.firstOrNull()?.description ?: "Desconhecido"
                // Você pode adicionar mais campos conforme necessário
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Erro ao buscar dados do tempo: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}