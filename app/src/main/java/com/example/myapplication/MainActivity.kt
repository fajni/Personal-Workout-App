package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private fun caloriesActivity(btn: View) {

        btn.setOnClickListener {

            val intent = Intent(this, CaloriesActivity::class.java)
            startActivity(intent)
        }
    }

    private fun workoutActivity(btn: View) {

        btn.setOnClickListener {

            val intent = Intent(this, WorkoutActivity::class.java)
            startActivity(intent)
        }
    }

    private fun exit(btn: Button) {

        btn.setOnClickListener {
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val caloriesBtn = findViewById<View>(R.id.caloriesBtn)
        val workoutBtn = findViewById<View>(R.id.workoutBtn)
        val exitBtn = findViewById<Button>(R.id.mainExitBtn)

        val todos = findViewById<TextView>(R.id.todos)

        caloriesActivity(caloriesBtn)
        workoutActivity(workoutBtn)
        exit(exitBtn)

        val todoString: String = "1. Implement Table and Calculations for Calories\n" +
                "2. Button to send data to Firebase\n" +
                "3. Implement Workout part\n" +
                "4. Check for optimization (optional)"

        todos.text = todoString
    }
}