package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.models.MealData

/*

    Recycler View Adapter

*/

class MealAdapter(private var mealsList: List<MealData>) : RecyclerView.Adapter<MealAdapter.MyViewHolder>() {

    // individual element
    class MyViewHolder(mealView: View) : RecyclerView.ViewHolder(mealView) {

        val mealTitle: TextView = mealView.findViewById(R.id.mealTitle)
        val mealIngredients: TextView = mealView.findViewById(R.id.mealIngredients)
        val textureImage: ImageView = mealView.findViewById(R.id.textureImage)
        val mealCalories: TextView = mealView.findViewById(R.id.mealCaloriesValue)
        val mealProteins: TextView = mealView.findViewById(R.id.mealProteinsValue)
        val mealCarbs: TextView = mealView.findViewById(R.id.mealCarbsValue)
        val mealFats: TextView = mealView.findViewById(R.id.mealFatsValue)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val mealView = LayoutInflater.from(parent.context).inflate(R.layout.meal_item, parent, false)

        return MyViewHolder(mealView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.mealTitle.text = mealsList[position].name.uppercase()

        holder.textureImage.setImageResource(R.drawable.meal_drink)
        if(mealsList[position].texture.equals("food"))
            holder.textureImage.setImageResource(R.drawable.meal_food)

        if(mealsList[position].ingredients.toString().length > 43) {
            holder.mealIngredients.text = mealsList[position].ingredients.toString()
                .replace("[", "")
                .replace("]", "")
                .substring(0, 40) + "..."
        } else {
            holder.mealIngredients.text = mealsList[position].ingredients.toString()
                .replace("[", "")
                .replace("]", "")
        }

        holder.mealCalories.text = mealsList[position].calories.toString() + " kcal"
        holder.mealProteins.text = mealsList[position].proteins.toString() + " g"
        holder.mealCarbs.text = mealsList[position].carbs.toString() + " g"
        holder.mealFats.text = mealsList[position].fats.toString() + " g"

        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Clicked on " + mealsList[position].name.uppercase(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return mealsList.size
    }
}