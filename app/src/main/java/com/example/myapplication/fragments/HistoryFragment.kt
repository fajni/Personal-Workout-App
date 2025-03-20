package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.CurrentDate
import com.example.myapplication.R
import com.example.myapplication.adapter.FoodAdapter
import com.example.myapplication.data.models.FoodData
import com.example.myapplication.data.viewmodel.FoodViewModel
import java.text.SimpleDateFormat
import java.util.Calendar

class HistoryFragment : Fragment() {

    private lateinit var foodViewModel: FoodViewModel

    private lateinit var chooseDate: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)

        foodViewModel = ViewModelProvider(this)[FoodViewModel::class.java]

        chooseDate = view.findViewById(R.id.chooseDate)
        chooseDate.setText(CurrentDate().getCurrentData())

        val recyclerView = view.findViewById<RecyclerView>(R.id.mealsList)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

        var mealsList = ArrayList<FoodData>()

        val adapter = FoodAdapter(mealsList, foodViewModel)

        foodViewModel.readAllData.observe(viewLifecycleOwner, Observer { meals ->

            meals.forEach { meal ->
                if(meal.date.equals(chooseDate.text.toString()))
                    mealsList.add(meal)
                if(chooseDate.text.isBlank() || chooseDate.text.length < 10)
                    mealsList.add(meal)
            }

            //adapter.setData(meals)
            adapter.setData(mealsList)
        })
        recyclerView.adapter = adapter

        return view
    }
}