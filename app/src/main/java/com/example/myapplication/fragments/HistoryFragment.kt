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
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.utils.CurrentDate
import com.example.myapplication.R
import com.example.myapplication.adapter.FoodAdapter
import com.example.myapplication.data.models.FoodData
import com.example.myapplication.data.viewmodel.FoodViewModel

class HistoryFragment : Fragment() {

    private lateinit var foodViewModel: FoodViewModel

    private lateinit var chooseDate: EditText
    private lateinit var btnClearDatabase: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)

        foodViewModel = ViewModelProvider(this)[FoodViewModel::class.java]

        chooseDate = view.findViewById(R.id.chooseDate)
        chooseDate.setText(CurrentDate().getCurrentData())

        btnClearDatabase = view.findViewById(R.id.historyBtnClearDatabase)

        val recyclerView = view.findViewById<RecyclerView>(R.id.mealsList)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

        var mealsList = ArrayList<FoodData>()

        val adapter = FoodAdapter(mealsList, foodViewModel)

        chooseDate.addTextChangedListener {
            mealsList.clear()
            foodViewModel.readAllData.observe(viewLifecycleOwner, Observer { meals ->

                meals.forEach { meal ->
                    if (meal.date.equals(chooseDate.text.toString()))
                        mealsList.add(meal)
                    if (chooseDate.text.isBlank() || chooseDate.text.length < 10)
                        mealsList.add(meal)
                }

                //adapter.setData(meals)
                adapter.setData(mealsList)
            })
            recyclerView.adapter = adapter
        }

        // CLEAR DATABASE
        val builder = AlertDialog.Builder(context)
        val positiveSpan = SpannableString("Yes").apply { setSpan(ForegroundColorSpan(Color.GREEN), 0, "Yes".length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE) }
        val negativeSpan = SpannableString("No").apply { setSpan(ForegroundColorSpan(Color.RED), 0, "No".length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE) }

        btnClearDatabase.setOnClickListener {

            builder.setMessage("Are you sure you want to clear database?")
                .setCancelable(true)
                .setTitle("Clear Food Database")

                .setPositiveButton(positiveSpan) { dialog, id ->
                    Toast.makeText(context, "Food Database cleared!", Toast.LENGTH_SHORT).show()
                    foodViewModel.deleteAll()
                    mealsList.clear()
                }

                .setNegativeButton(negativeSpan) { dialog, id ->
                    dialog.dismiss()
                }
                .show()

        }

        return view
    }
}