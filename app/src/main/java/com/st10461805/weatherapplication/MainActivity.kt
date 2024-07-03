package com.st10461805.weatherapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/*
* The IIE. 2024. Introduction To Mobile Application [IMAD5112 Module Outline].
* The Independent Institute of Education: Unpublished
* */

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val mainScreenButton = findViewById<Button>(R.id.buttonMainScreen)

        mainScreenButton.setOnClickListener() {
            val mainScreenIntent = Intent(this, MainScreen::class.java)
            startActivity(mainScreenIntent)
        }


    }
}