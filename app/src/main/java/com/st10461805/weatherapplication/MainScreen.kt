package com.st10461805.weatherapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainScreen : AppCompatActivity() {
    @SuppressLint("WrongViewCast", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Text views
        val subHeading = findViewById<TextView>(R.id.textSubheading)
        subHeadingView = subHeading
        subHeadingText = subHeadingView.text.toString()

        // Edit views
        val dayInput = findViewById<EditText>(R.id.editTextWeatherDay)
        val minInput = findViewById<EditText>(R.id.editTextWeatherMin)
        val maxInput = findViewById<EditText>(R.id.editTextWeatherMax)
        val condInput = findViewById<EditText>(R.id.editTextWeatherCondition)

        dayInputView = dayInput
        minInputView = minInput
        maxInputView = maxInput
        condInputView = condInput

        // Buttons
        val storeButton = findViewById<Button>(R.id.buttonStoreInput)
        val clearButton = findViewById<Button>(R.id.buttonClearInput)
        val detailButton = findViewById<Button>(R.id.buttonForecast)

        detailButton.setOnClickListener() {
            val detailScreenIntent = Intent(this, DetailScreen::class.java)
            startActivity(detailScreenIntent)
        }

        // Store the values in their respective arrays when we press the button
        storeButton.setOnClickListener() {
            val dayValue = dayInputView.text.toString()
            // Max values array
            storeValueInput(dayValue, maxInputView, daysWeatherMax)
            // Min values array
            storeValueInput(dayValue, minInputView, daysWeatherMin)

            val viewValue = condInputView.text.toString()
            val dayIndex = daysOfTheWeek.indexOf(dayValue)
            daysCondition.add(dayIndex, viewValue)

            displayAverageValues()

            subHeadingView.text = "$dayValue weather details stored!"
        }

        clearButton.setOnClickListener() {
            clearInputViews()
        }

    }

    // Globalised variables for us to use in different methods
    private lateinit var dayInputView: EditText
    private lateinit var minInputView: EditText
    private lateinit var maxInputView: EditText
    private lateinit var condInputView: EditText
    private lateinit var subHeadingView: TextView
    private lateinit var subHeadingText: String

    @SuppressLint("SetTextI18n")
    private fun storeValueInput(day: String, view: EditText, list: MutableList<Int>) {
        val viewValue = view.text.toString().toInt()
        val dayIndex = daysOfTheWeek.indexOf(day)
        list.add(dayIndex, viewValue)
    }
    private fun clearInputViews() {
        val inputViews = arrayOf(dayInputView, minInputView, maxInputView)

        for (view in inputViews) {
            view.setText("")
        }
    }

    // Takes in an array of integers and loops through the values to add them together
    // and then dividing it by the length of the array to get the average
    private fun calculateAverageValues(values: MutableList<Int>): Int {
        val arrayLength = values.count()

        var initialValue = 0
        for (value in values) {
            initialValue += value
        }

        return initialValue / arrayLength
    }

    // Calculates the minimum and maximum average before finding the average of the respective values
    // and then displaying that
    @SuppressLint("SetTextI18n")
    private fun displayAverageValues() {
        val maxAverage = calculateAverageValues(daysWeatherMax)
        val minAverage = calculateAverageValues(daysWeatherMin)

        val finalAverage = (maxAverage + minAverage) / 2
        val finalAverageString = finalAverage.toString()

        val weatherValueView = findViewById<TextView>(R.id.textWeatherValue)
        weatherValueView.text = finalAverageString + " °C"
    }
}