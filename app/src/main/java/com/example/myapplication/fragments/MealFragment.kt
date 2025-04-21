package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapter.MealAdapter
import com.example.myapplication.data.models.MealData
import com.example.myapplication.data.viewmodel.MealViewModel

class MealFragment : Fragment() {

    private lateinit var mealViewModel: MealViewModel

    private lateinit var btnAddMeal: Button

    private lateinit var mealsLayout: FrameLayout
    private lateinit var mealsRecyclerView: RecyclerView

    private lateinit var noValuesText: LinearLayout


    private fun initializeResources(view: View) {

        mealViewModel = ViewModelProvider(this)[MealViewModel::class.java]

        btnAddMeal = view.findViewById(R.id.btnAddMeal)

        mealsLayout = view.findViewById(R.id.mealsLayout)

        mealsRecyclerView = view.findViewById(R.id.mealsRecyclerView)
        mealsRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mealsRecyclerView.setHasFixedSize(true)

        noValuesText = view.findViewById(R.id.noValuesText)
    }

    private fun setData() {

        val adapter = MealAdapter()

        mealViewModel.readAllMeals.observe(viewLifecycleOwner, Observer { mealsList ->

            if(mealsList.isEmpty()) {

                noValuesText.isVisible = true
                mealsLayout.isVisible = false
            }
            else {

                noValuesText.isVisible = false
                mealsLayout.isVisible = true

                adapter.setData(mealsList as ArrayList<MealData>)
            }
        })

        mealsRecyclerView.adapter = adapter

        btnAddMeal.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.mainFrameLayout, MealAddFragment())
                .addToBackStack(null)
                .commit()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_meal, container, false)

        initializeResources(view)

        setData()

        return view
    }
}