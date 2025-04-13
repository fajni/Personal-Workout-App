package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.myapplication.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MealAddFragment : Fragment() {

    private lateinit var btnAddMeal: Button
    private lateinit var btnCloseAddMeal: Button


    private fun initializeResources(view: View) {

        btnAddMeal = view.findViewById(R.id.btnMealSubmit)
        btnCloseAddMeal = view.findViewById(R.id.btnCloseAddMeal)

    }

    private fun setData() {

        btnAddMeal.setOnClickListener {
            Toast.makeText(context, "Add", Toast.LENGTH_SHORT).show()
        }

        btnCloseAddMeal.setOnClickListener {
            requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView).selectedItemId = R.id.meals

            parentFragmentManager.beginTransaction()
                .replace(R.id.mainFrameLayout, FoodFragment())
                .commit()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_meal_add, container, false)

        initializeResources(view)

        setData()

        return view
    }
}