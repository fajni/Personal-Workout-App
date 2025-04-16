package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.data.models.MealData
import com.example.myapplication.data.viewmodel.MealViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MealAddFragment : Fragment() {

    private lateinit var mealViewModel: MealViewModel

    private lateinit var btnSubmitMeal: Button
    private lateinit var btnCloseAddMeal: ImageButton

    private lateinit var addMealImageView: ImageView

    private lateinit var mealName: EditText
    private lateinit var mealTexture: Spinner
    private lateinit var mealIngredients: EditText
    private lateinit var mealDesc: EditText
    private lateinit var mealCalories: EditText
    private lateinit var mealProteins: EditText
    private lateinit var mealCarbs: EditText
    private lateinit var mealFats: EditText


    private fun setSpinner(spinner: Spinner) {

        val textures = listOf("Drink", "Food")
        val adapter = ArrayAdapter(requireContext(), R.layout.item_dropdown, textures)

        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    private fun initializeResources(view: View) {

        mealViewModel = ViewModelProvider(this)[MealViewModel::class.java]

        btnSubmitMeal = view.findViewById(R.id.btnMealSubmit)
        btnCloseAddMeal = view.findViewById(R.id.btnCloseAddMeal)

        addMealImageView = view.findViewById(R.id.addMealImageView)

        mealName = view.findViewById(R.id.addMealName)

        mealTexture = view.findViewById(R.id.addMealTexture)
        setSpinner(mealTexture)

        mealIngredients = view.findViewById(R.id.addMealIngredients)
        mealDesc = view.findViewById(R.id.addMealDesc)
        mealCalories = view.findViewById(R.id.addMealCalories)
        mealProteins = view.findViewById(R.id.addMealProteins)
        mealCarbs = view.findViewById(R.id.addMealCarbs)
        mealFats = view.findViewById(R.id.addMealFats)
    }

    private fun blankFields(): Boolean {

        if(mealName.text.isBlank() ||
            mealTexture.selectedItem.toString().isBlank() ||
            mealIngredients.text.isBlank() ||
            mealCalories.text.isBlank() ||
            mealProteins.text.isBlank() ||
            mealCarbs.text.isBlank() ||
            mealFats.text.isBlank()){

            return true
        }

        return false
    }

    private fun setButtons() {

        btnSubmitMeal.setOnClickListener {

            if(blankFields()) {

                Toast.makeText(context, "No blank fields allowed!", Toast.LENGTH_SHORT).show()
            }
            else {

                val meal = MealData(
                    id = 0,
                    name = mealName.text.toString(),
                    texture = mealTexture.selectedItem.toString(),
                    ingredients = mealIngredients.text.toString(),
                    description = mealDesc.text.toString(),
                    calories = mealCalories.text.toString().toInt(),
                    proteins = mealProteins.text.toString().toInt(),
                    carbs = mealCarbs.text.toString().toInt(),
                    fats = mealFats.text.toString().toInt()
                )

                try{

                    mealViewModel.addMeal(meal)
                    Toast.makeText(context, "Added new meal - " + meal.name, Toast.LENGTH_SHORT).show()

                }catch (e: Exception) {
                    println(e.message)
                    Toast.makeText(context, "ERROR - " + e.message, Toast.LENGTH_SHORT).show()
                }

                parentFragmentManager.beginTransaction()
                    .replace(R.id.mainFrameLayout, MealFragment())
                    .commit()
            }

        }

        btnCloseAddMeal.setOnClickListener {
            requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView).selectedItemId = R.id.meals

            parentFragmentManager.beginTransaction()
                .replace(R.id.mainFrameLayout, MealFragment())
                .commit()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_meal_add, container, false)

        initializeResources(view)

        setButtons()

        addMealImageView.animate()
            .rotationYBy(360f)
            .setDuration(1800)
            .setStartDelay(1500)
            .start()

        return view
    }
}