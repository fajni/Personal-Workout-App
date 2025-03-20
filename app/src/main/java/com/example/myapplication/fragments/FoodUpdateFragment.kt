package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.data.models.FoodData
import com.example.myapplication.data.viewmodel.FoodViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView


class FoodUpdateFragment(private var foodData: FoodData) : Fragment() {

    private lateinit var foodViewModel: FoodViewModel

    private lateinit var title: EditText
    private lateinit var calories: EditText
    private lateinit var proteins: EditText
    private lateinit var carbs: EditText
    private lateinit var fats: EditText
    private lateinit var date: EditText

    private lateinit var updateBtn: Button

    private fun setFields() {

        title.setText(foodData.title!!)
        calories.setText(foodData.calories!!.toString())
        proteins.setText(foodData.proteins!!.toString())
        carbs.setText(foodData.carbs!!.toString())
        fats.setText(foodData.fats!!.toString())
        date.setText(foodData.date!!.toString())
    }

    private fun checkBlankFields(): Boolean {

        if (
            title.text.isBlank() ||
            calories.text.isBlank() ||
            proteins.text.isBlank() ||
            carbs.text.isBlank() ||
            fats.text.isBlank() ||
            date.text.isBlank()
        )
            return false

        return true
    }

    private fun updateData() {

        val updatedFood = FoodData(
            foodData.number,
            title.text.toString(),
            calories.text.toString().toInt(),
            proteins.text.toString().toInt(),
            carbs.text.toString().toInt(),
            fats.text.toString().toInt(),
            date.text.toString()
            )

        foodViewModel.updateFood(updatedFood)

        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView).selectedItemId = R.id.food

        parentFragmentManager.beginTransaction()
            .replace(R.id.mainFrameLayout, FoodFragment())
            .commit()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_update_food, container, false)

        foodViewModel = ViewModelProvider(this)[FoodViewModel::class.java]

        title = view.findViewById(R.id.updateFoodTitle)
        calories = view.findViewById(R.id.updateFoodCalories)
        proteins = view.findViewById(R.id.updateFoodProteins)
        carbs = view.findViewById(R.id.updateFoodCarbs)
        fats = view.findViewById(R.id.updateFoodFats)
        date = view.findViewById(R.id.updateFoodDate)
        updateBtn = view.findViewById(R.id.updateFoodBtn)

        setFields()

        updateBtn.setOnClickListener {

            if(checkBlankFields()){
                updateData()
                Toast.makeText(context, "Updated " + title.text.toString().uppercase() , Toast.LENGTH_SHORT).show()
            }
            else
                Toast.makeText(context, "Empty Fields Not Allowed!", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}