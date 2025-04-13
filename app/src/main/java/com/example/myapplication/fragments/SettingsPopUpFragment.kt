package com.example.myapplication.fragments

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.myapplication.R
import com.example.myapplication.data.database.AppDatabase

class SettingsPopUpFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings_pop_up, container, false)

        val btnDeleteAppDatabase = view.findViewById<Button>(R.id.btnDeleteAppDatabase)
        val btnDropFoodTable = view.findViewById<Button>(R.id.btnDropFoodTable)
        val btnDropAccountTable = view.findViewById<Button>(R.id.btnDropAccountTable)
        val btnDropWorkoutTable = view.findViewById<Button>(R.id.btnDropWorkoutTable)
        val btnDropMealTable = view.findViewById<Button>(R.id.btnDropMealTable)

        val builder = AlertDialog.Builder(context)
        val positiveSpan = SpannableString("Yes").apply { setSpan(ForegroundColorSpan(Color.GREEN), 0, "Yes".length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE) }
        val negativeSpan = SpannableString("No").apply { setSpan(ForegroundColorSpan(Color.RED), 0, "No".length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE) }

        btnDeleteAppDatabase.setOnClickListener {

            builder.setMessage("Are you sure you want to delete Database?")
                .setCancelable(true)
                .setTitle("Delete App Database")

                .setPositiveButton(positiveSpan) { dialog, id ->
                    try{

                        context?.applicationContext?.deleteDatabase("AppDatabase")
                        Toast.makeText(context, "Deleted App Database", Toast.LENGTH_SHORT).show()

                    } catch (e: Exception) {

                        Toast.makeText(context, "Deleting App Database ERROR", Toast.LENGTH_SHORT).show()
                        e.message
                    }
                }

                .setNegativeButton(negativeSpan) { dialog, id -> dialog.dismiss()}
                .show()

        }

        btnDropFoodTable.setOnClickListener {

            builder.setMessage("Are you sure you want to drop Food table?")
                .setCancelable(true)
                .setTitle("Drop Food Table")

                .setPositiveButton(positiveSpan) { dialog, id ->
                    try {

                        val db = AppDatabase.getDatabase(context?.applicationContext!!)
                        val sqliteDb = db.openHelper.writableDatabase

                        sqliteDb.execSQL("DROP TABLE IF EXISTS food_data")

                        Toast.makeText(context, "Deleted Food Table", Toast.LENGTH_SHORT).show()

                    } catch (e: Exception) {

                        Toast.makeText(context, "Deleting Food Table ERROR",Toast.LENGTH_SHORT).show()
                        e.message
                    }
                }

                .setNegativeButton(negativeSpan) { dialog, id -> dialog.dismiss()}
                .show()
        }

        btnDropAccountTable.setOnClickListener {

            builder.setMessage("Are you sure you want to drop Account table?")
                .setCancelable(true)
                .setTitle("Drop Account Table")

                .setPositiveButton(positiveSpan) { dialog, id ->

                    try {

                        val db = AppDatabase.getDatabase(context?.applicationContext!!)
                        val sqliteDb = db.openHelper.writableDatabase

                        sqliteDb.execSQL("DROP TABLE IF EXISTS account_data")

                        Toast.makeText(context, "Deleted Account Table", Toast.LENGTH_SHORT).show()

                    } catch (e: Exception) {

                        Toast.makeText(context, "Deleting Account Table ERROR",Toast.LENGTH_SHORT).show()
                        e.message
                    }
                }

                .setNegativeButton(negativeSpan) { dialog, id -> dialog.dismiss()}
                .show()
        }

        btnDropWorkoutTable.setOnClickListener {

            builder.setMessage("Are you sure you want to drop Workout table?")
                .setCancelable(true)
                .setTitle("Drop Workout Table")

                .setPositiveButton(positiveSpan) { dialog, id ->

                    try {

                        val db = AppDatabase.getDatabase(context?.applicationContext!!)
                        val sqliteDb = db.openHelper.writableDatabase

                        sqliteDb.execSQL("DROP TABLE IF EXISTS workout_data")

                        Toast.makeText(context, "Deleted Workout Table", Toast.LENGTH_SHORT).show()

                    } catch (e: Exception) {

                        Toast.makeText(context, "Deleting Workout Table ERROR",Toast.LENGTH_SHORT).show()
                        e.message
                    }
                }

                .setNegativeButton(negativeSpan) { dialog, id -> dialog.dismiss()}
                .show()
        }

        btnDropMealTable.setOnClickListener {

            builder.setMessage("Are you sure you want to drop Meal table?")
                .setCancelable(true)
                .setTitle("Drop Meal Table")

                .setPositiveButton(positiveSpan) { dialog, id ->

                    try {

                        val db = AppDatabase.getDatabase(context?.applicationContext!!)
                        val sqliteDb = db.openHelper.writableDatabase

                        sqliteDb.execSQL("DROP TABLE IF EXISTS meal_data")

                        Toast.makeText(context, "Deleted Meal Table", Toast.LENGTH_SHORT).show()

                    } catch (e: Exception) {

                        Toast.makeText(context, "Deleting Meal Table ERROR",Toast.LENGTH_SHORT).show()
                        e.message
                    }
                }

                .setNegativeButton(negativeSpan) { dialog, id -> dialog.dismiss()}
                .show()
        }

        return view
    }
}