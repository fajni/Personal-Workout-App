package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.utils.CurrentDate
import com.example.myapplication.R
import com.example.myapplication.adapter.FoodAdapter
import com.example.myapplication.data.models.FoodData
import com.example.myapplication.data.viewmodel.FoodViewModel

class FoodFragment : Fragment() {

    private lateinit var foodViewModel: FoodViewModel

    private lateinit var linearLayoutHistory: LinearLayout

    private lateinit var caloriesValue: TextView
    private lateinit var proteinsValue: TextView
    private lateinit var carbsValue: TextView
    private lateinit var fatsValue: TextView
    private lateinit var fibersValue: TextView

    private lateinit var noValuesText: LinearLayout

    private lateinit var foodRecyclerView: RecyclerView


    private fun setData() {

        val adapter = FoodAdapter(ArrayList<FoodData>(), foodViewModel)

        foodViewModel.readAllData.observe(viewLifecycleOwner, Observer { foodList ->

            adapter.setCurrentData(foodList)
            calculateValues(foodList)
        })

        foodRecyclerView.adapter = adapter
    }

    private fun calculateValues(list: List<FoodData>) {

        val currentDate = CurrentDate().getCurrentData()

        val todayFood = ArrayList<FoodData>()

        var calories: Int = 0
        var proteins: Int = 0
        var carbs: Int = 0
        var fats: Int = 0
        var fibers: Int = 0

        for (food in list){
            if(food.date == currentDate) {

                calories += food.calories!!
                proteins += food.proteins!!
                carbs += food.carbs!!
                fats += food.fats!!
                fibers += food.fibers!!

                todayFood.add(food)
            }
        }

        if(todayFood.isNotEmpty()){

            foodRecyclerView.isVisible = true
            noValuesText.isVisible = false
        }

        caloriesValue.text = calories.toString() + " kcal"
        proteinsValue.text = proteins.toString() + " g"
        carbsValue.text = carbs.toString() + " g"
        fatsValue.setText(fats.toString() + " g")
        fibersValue.setText(fibers.toString() + " g")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_food, container, false)

        linearLayoutHistory = view.findViewById<LinearLayout>(R.id.linearLayoutHistory)

        caloriesValue = view.findViewById<TextView>(R.id.caloriesValue)
        proteinsValue = view.findViewById<TextView>(R.id.proteinsValue)
        carbsValue = view.findViewById<TextView>(R.id.carbsValue)
        fatsValue = view.findViewById<TextView>(R.id.fatsValue)
        fibersValue = view.findViewById<TextView>(R.id.fibersValue)

        noValuesText = view.findViewById<LinearLayout>(R.id.noValuesText)

        foodRecyclerView = view.findViewById<RecyclerView>(R.id.foodList)
        foodRecyclerView.layoutManager = LinearLayoutManager(context)
        foodRecyclerView.setHasFixedSize(true)

        foodViewModel = ViewModelProvider(this)[FoodViewModel::class.java]

        // Set ADD Button to VISIBLE
        val addBtn = requireActivity().findViewById<ImageButton>(R.id.addBtn)
        addBtn.isVisible = true

        // TODO: set outline for progressTable to red if (calories < account.calories)

        setData()

        linearLayoutHistory.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.mainFrameLayout, HistoryFragment())
                .addToBackStack(null)
                .commit()
        }

        return view
    }
}