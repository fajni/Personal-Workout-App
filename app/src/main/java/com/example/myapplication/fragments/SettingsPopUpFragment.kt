package com.example.myapplication.fragments

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.myapplication.R

class SettingsPopUpFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings_pop_up, container, false)

        val btnDeleteFood = view.findViewById<Button>(R.id.btnDeleteFoodDatabase)
        val btnDeleteAccount = view.findViewById<Button>(R.id.btnDeleteAccountDatabase)
        val btnDeleteWorkout = view.findViewById<Button>(R.id.btnDeleteWorkoutDatabase)

        val builder = AlertDialog.Builder(context)
        val positiveSpan = SpannableString("Yes").apply { setSpan(ForegroundColorSpan(Color.GREEN), 0, "Yes".length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE) }
        val negativeSpan = SpannableString("No").apply { setSpan(ForegroundColorSpan(Color.RED), 0, "No".length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE) }

        btnDeleteFood.setOnClickListener {

            builder.setMessage("Are you sure you want to delete FoodDatabase?")
                .setCancelable(true)
                .setTitle("Delete Food Database")

                .setPositiveButton(positiveSpan) { dialog, id ->
                    try{

                        context?.applicationContext?.deleteDatabase("FoodDatabase")
                        Toast.makeText(context, "Deleted Food Database", Toast.LENGTH_SHORT).show()

                    } catch (e: Exception) {

                        Toast.makeText(context, "Deleting Food Database ERROR", Toast.LENGTH_SHORT).show()
                        e.message
                    }
                }

                .setNegativeButton(negativeSpan) { dialog, id -> dialog.dismiss()}
                .show()

        }

        btnDeleteAccount.setOnClickListener {

            builder.setMessage("Are you sure you want to delete AccountDatabase?")
                .setCancelable(true)
                .setTitle("Delete Account Database")

                .setPositiveButton(positiveSpan) { dialog, id ->
                    try {

                        context?.applicationContext?.deleteDatabase("AccountDatabase")
                        Toast.makeText(context, "Deleted Account Database", Toast.LENGTH_SHORT)
                            .show()

                    } catch (e: Exception) {

                        Toast.makeText(
                            context,
                            "Deleting Account Database ERROR",
                            Toast.LENGTH_SHORT
                        ).show()
                        e.message
                    }
                }

                .setNegativeButton(negativeSpan) { dialog, id -> dialog.dismiss()}
                .show()
        }

        btnDeleteWorkout.setOnClickListener {

            builder.setMessage("Are you sure you want to delete WorkoutDatabase?")
                .setCancelable(true)
                .setTitle("Delete Workout Database")

                .setPositiveButton(positiveSpan) { dialog, id ->

                    try {

                        context?.applicationContext?.deleteDatabase("WorkoutDatabase")
                        Toast.makeText(context, "Deleted Workout Database", Toast.LENGTH_SHORT)
                            .show()

                    } catch (e: Exception) {

                        Toast.makeText(
                            context,
                            "Deleting Workout Database ERROR",
                            Toast.LENGTH_SHORT
                        ).show()
                        e.message
                    }
                }

                .setNegativeButton(negativeSpan) { dialog, id -> dialog.dismiss()}
                .show()
        }

        return view
    }
}