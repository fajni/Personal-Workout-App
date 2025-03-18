package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapter.FoodAdapter
import com.example.myapplication.data.models.FoodData
import com.example.myapplication.data.viewmodel.FoodViewModel

class HistoryFragment : Fragment() {

    private lateinit var foodViewModel: FoodViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)

        foodViewModel = ViewModelProvider(this)[FoodViewModel::class.java]

        val recyclerView = view.findViewById<RecyclerView>(R.id.mealsList)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

        var mealsList = ArrayList<FoodData>()

        val adapter = FoodAdapter(mealsList, foodViewModel)

        foodViewModel.readAllData.observe(viewLifecycleOwner, Observer { meals ->

            adapter.setData(meals)
        })

        recyclerView.adapter = adapter

        return view
    }
}