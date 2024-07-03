package com.st10461805.weatherapplication

class DayOfTheWeek(dayName: String, dayMin: Int, dayMax: Int) {
    var name = ""
    var minimum = 0
    var maximum = 0

    init {
        name = dayName
        minimum = dayMin
        maximum = dayMax
    }
}

// Globally declared variables that can be used across the application
var daysOfTheWeek =
    arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
var daysCondition = mutableListOf<String>()
var daysWeatherMin = mutableListOf<Int>()
var daysWeatherMax = mutableListOf<Int>()