package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class CaloriesActivity : AppCompatActivity() {

    private fun exit(exitBtn: Button) {
        exitBtn.setOnClickListener {
            finish()
        }
    }

    // START (MAIN METHOD)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_calories)

        val exitBtn = findViewById<Button>(R.id.exitBtn)
        exit(exitBtn)

    }
}