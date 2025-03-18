package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapter.FoodAdapter
import com.example.myapplication.data.models.FoodData
import com.example.myapplication.data.viewmodel.FoodViewModel
import java.text.SimpleDateFormat
import java.util.Calendar

class FoodFragment : Fragment() {

    private lateinit var foodViewModel: FoodViewModel

    private lateinit var caloriesValue: TextView
    private lateinit var proteinsValue: TextView
    private lateinit var carbsValue: TextView
    private lateinit var fatsValue: TextView

    private lateinit var mealsRecyclerView: RecyclerView

    private lateinit var mealsList: ArrayList<FoodData>


    private fun setValues() {

        mealsList = ArrayList<FoodData>()

        val adapter = FoodAdapter(mealsList, foodViewModel)

        foodViewModel.readAllData.observe(viewLifecycleOwner, Observer { meals ->

            adapter.setCurrentData(meals)
            calculateValues(meals)
        })

        mealsRecyclerView.adapter = adapter
    }

    private fun calculateValues(mealsList: List<FoodData>) {

        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        var currentDate: String = formatter.format(time)

        var calories: Int = 0
        var proteins: Int = 0
        var carbs: Int = 0
        var fats: Int = 0

        for (meal in mealsList){
            if(meal.date == currentDate) {

                calories += meal.calories!!
                proteins += meal.proteins!!
                carbs += meal.carbs!!
                fats += meal.fats!!
            }
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

        val view: View = inflater.inflate(R.layout.fragment_food, container, false)

        caloriesValue = view.findViewById<TextView>(R.id.caloriesValue)
        proteinsValue = view.findViewById<TextView>(R.id.proteinsValue)
        carbsValue = view.findViewById<TextView>(R.id.carbsValue)
        fatsValue = view.findViewById<TextView>(R.id.fatsValue)

        mealsRecyclerView = view.findViewById<RecyclerView>(R.id.mealsList)
        mealsRecyclerView.layoutManager = LinearLayoutManager(context)
        mealsRecyclerView.setHasFixedSize(true)

        foodViewModel = ViewModelProvider(this)[FoodViewModel::class.java]

        setValues()

        return view
    }
}