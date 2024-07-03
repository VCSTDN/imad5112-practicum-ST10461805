package com.st10461805.weatherapplication

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val detailWeather = findViewById<TextView>(R.id.detailWeatherText)
        var detailWeatherString = ""

        // Loop through arrays using the days of the week array element indexes to reference the details in other arrays
        // and use those details to concatenate the string
        for ((index, day) in daysOfTheWeek.withIndex()) {
            detailWeatherString += "$day     Min: ${daysWeatherMin[index]}     Max: ${daysWeatherMax[index]}     Condition: ${daysCondition[index]}\n\n"
        }

        detailWeather.text = detailWeatherString
    }
}