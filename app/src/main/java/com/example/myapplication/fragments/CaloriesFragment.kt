package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapter.MealAdapter
import com.example.myapplication.models.MealData

class CaloriesFragment : Fragment() {

    private lateinit var caloriesValue: TextView
    private lateinit var proteinsValue: TextView
    private lateinit var carbsValue: TextView
    private lateinit var fatsValue: TextView

    private lateinit var mealsRecyclerView: RecyclerView

    private lateinit var mealsList: ArrayList<MealData>

    // TODO
    private fun setValues() {

        caloriesValue.text = "/ kcal"
        proteinsValue.text = "/ g"
        carbsValue.text = "/ g"
        fatsValue.setText("/ g")

        var meal1: MealData = MealData(1, 500, 40, 70, 5)
        var meal2: MealData = MealData(2, 800, 70, 100, 20)
        var meal3: MealData = MealData(3, 1000, 50, 60, 25)

        mealsList = arrayListOf<MealData>()
        mealsList.add(meal1)
        mealsList.add(meal2)
        mealsList.add(meal3)

        mealsRecyclerView.adapter = MealAdapter(mealsList)

        calculateValues(mealsList)
    }

    // TODO
    private fun calculateValues(mealsList: List<MealData>) {

        var calories: Int = 0
        var proteins: Int = 0
        var carbs: Int = 0
        var fats: Int = 0

        for (meal in mealsList){
            calories += meal.calories!!
            proteins += meal.proteins!!
            carbs += meal.carbs!!
            fats += meal.fats!!
        }

        caloriesValue.text = calories.toString() + "kcal"
        proteinsValue.text = proteins.toString() + "g"
        carbsValue.text = carbs.toString() + "g"
        fatsValue.setText(fats.toString() + "g")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_calories, container, false)

        caloriesValue = view.findViewById<TextView>(R.id.caloriesValue)
        proteinsValue = view.findViewById<TextView>(R.id.proteinsValue)
        carbsValue = view.findViewById<TextView>(R.id.carbsValue)
        fatsValue = view.findViewById<TextView>(R.id.fatsValue)

        mealsRecyclerView = view.findViewById<RecyclerView>(R.id.mealsList)
        mealsRecyclerView.layoutManager = LinearLayoutManager(context)
        mealsRecyclerView.setHasFixedSize(true)

        setValues()

        val addBtn = view.findViewById<ImageButton>(R.id.addBtn)

        // Change CaloriesFragment to AddFragment
        addBtn?.setOnClickListener {

            val transaction = parentFragmentManager.beginTransaction()

            transaction.replace(R.id.mainFrameLayout, AddFragment())
            transaction.commit()

            Toast.makeText(context, "Add Fragment", Toast.LENGTH_SHORT).show()

        }

        return view
    }
}