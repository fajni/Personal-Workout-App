package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.MealData

class MealAdapter (private val mealsList: List<MealData>) : RecyclerView.Adapter<MealAdapter.MyViewHolder>() {

    // individual element
    class MyViewHolder(mealView: View) : RecyclerView.ViewHolder(mealView) {

        val number: TextView = mealView.findViewById(R.id.mealNumber)
        val calories: TextView = mealView.findViewById(R.id.mealCaloriesValue)
        val proteins: TextView = mealView.findViewById(R.id.mealProteinsValue)
        val carbs: TextView = mealView.findViewById(R.id.mealCarbsValue)
        val fats: TextView = mealView.findViewById(R.id.mealFatsValue)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val mealView = LayoutInflater.from(parent.context).inflate(R.layout.meal_item, parent, false)

        return MyViewHolder(mealView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.number.text = "Meal No." + mealsList[position].number.toString()
        holder.calories.text = mealsList[position].calories.toString() + "kcal"
        holder.proteins.text = mealsList[position].proteins.toString() + "g"
        holder.carbs.text = mealsList[position].carbs.toString() + "g"
        holder.fats.text = mealsList[position].fats.toString() + "g"
    }

    override fun getItemCount(): Int {
        return mealsList.size
    }

}