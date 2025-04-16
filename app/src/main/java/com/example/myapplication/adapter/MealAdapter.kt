package com.example.myapplication.adapter

import android.app.AlertDialog
import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.models.FoodData
import com.example.myapplication.data.models.MealData
import com.example.myapplication.data.viewmodel.FoodViewModel
import com.example.myapplication.fragments.MealInfoFragment
import com.example.myapplication.utils.CurrentDate

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
        val linearLayoutAddMealToMacros: LinearLayout = mealView.findViewById(R.id.linearLayoutAddMealToMacros)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val mealView = LayoutInflater.from(parent.context).inflate(R.layout.meal_item, parent, false)

        return MyViewHolder(mealView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.mealTitle.text = mealsList[position].name.uppercase()

        holder.textureImage.setImageResource(R.drawable.meal_drink_symbol)
        holder.mealTitle.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.discord_blue))
        if(mealsList[position].texture.equals("Food")){
            holder.textureImage.setImageResource(R.drawable.meal_food_symbol)
            holder.mealTitle.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.discord_orange))
        }

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

        // from adapter to fragment
        holder.itemView.setOnClickListener {

            val mealInfoFragment: MealInfoFragment = MealInfoFragment(mealsList[position])

            val fragmentTransaction = (holder.itemView.context as AppCompatActivity).supportFragmentManager.beginTransaction()

            fragmentTransaction.replace(R.id.mainFrameLayout, mealInfoFragment)
            fragmentTransaction.addToBackStack(null) //go back
            fragmentTransaction.commit()
        }

        holder.linearLayoutAddMealToMacros.setOnClickListener {

            val builder = AlertDialog.Builder(holder.itemView.context)
            val positiveSpan = SpannableString("Yes").apply { setSpan(ForegroundColorSpan(Color.GREEN), 0, "Yes".length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE) }
            val negativeSpan = SpannableString("No").apply { setSpan(ForegroundColorSpan(Color.RED), 0, "No".length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE) }

            builder.setMessage("Are you sure you want to add this meal to current macros? ")
                .setCancelable(true)
                .setTitle("Add " + holder.mealTitle.text.toString().uppercase() + " to current Macros")

                .setPositiveButton(positiveSpan) { dialog, id ->

                    try{
                        val foodViewModel: FoodViewModel = ViewModelProvider(holder.itemView.context as AppCompatActivity)[FoodViewModel::class.java]

                        val food: FoodData = FoodData(
                            number = 0,
                            title = holder.mealTitle.text.toString(),
                            calories = holder.mealCalories.text.toString().replace(" kcal", "").toInt(),
                            proteins = holder.mealProteins.text.toString().replace(" g", "").toInt(),
                            carbs = holder.mealCarbs.text.toString().replace(" g", "").toInt(),
                            fats = holder.mealFats.text.toString().replace(" g", "").toInt(),
                            fibers = 0,
                            date = CurrentDate().getCurrentDate()
                        )

                        foodViewModel.addFood(food)

                        Toast.makeText(holder.itemView.context, "Added " + holder.mealTitle.text.toString() + " to Macros List", Toast.LENGTH_SHORT).show()

                    } catch (e: Exception) {
                        Toast.makeText(holder.itemView.context, "ERROR - " + e.message, Toast.LENGTH_SHORT).show()
                        println(e.message)
                    }
                }

                .setNegativeButton(negativeSpan) { dialog, id ->
                    dialog.dismiss()
                }
                .show()
        }

    }

    override fun getItemCount(): Int {
        return mealsList.size
    }
}