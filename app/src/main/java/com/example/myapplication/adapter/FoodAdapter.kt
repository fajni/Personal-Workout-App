package com.example.myapplication.adapter

import android.app.AlertDialog
import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.utils.CurrentDate
import com.example.myapplication.R
import com.example.myapplication.data.models.FoodData
import com.example.myapplication.data.viewmodel.FoodViewModel
import com.example.myapplication.fragments.FoodUpdateFragment

/*

    Recycler View Adapter

*/

class FoodAdapter (private var mealsList: ArrayList<FoodData>, private val foodViewModel: FoodViewModel) : RecyclerView.Adapter<FoodAdapter.MyViewHolder>() {

    // individual element
    class MyViewHolder(mealView: View) : RecyclerView.ViewHolder(mealView) {

        val title: TextView = mealView.findViewById(R.id.foodTitle)
        val calories: TextView = mealView.findViewById(R.id.mealCaloriesValue)
        val proteins: TextView = mealView.findViewById(R.id.mealProteinsValue)
        val carbs: TextView = mealView.findViewById(R.id.mealCarbsValue)
        val fats: TextView = mealView.findViewById(R.id.mealFatsValue)
        val deleteBtn: ImageButton = mealView.findViewById(R.id.deleteFood)
        val seeDetails: TextView = mealView.findViewById(R.id.textSeeDetails)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val mealView = LayoutInflater.from(parent.context).inflate(R.layout.food_item, parent, false)

        return MyViewHolder(mealView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.title.text = mealsList[position].title?.uppercase() + " / No." + mealsList[position].number.toString()
        holder.calories.text = mealsList[position].calories.toString() + "kcal"
        holder.proteins.text = mealsList[position].proteins.toString() + "g"
        holder.carbs.text = mealsList[position].carbs.toString() + "g"
        holder.fats.text = mealsList[position].fats.toString() + "g"

        // from adapter to fragment
        holder.itemView.setOnClickListener {

            val foodUpdateFragment: FoodUpdateFragment = FoodUpdateFragment(mealsList[position])

            val fragmentTransaction = (holder.itemView.context as AppCompatActivity).supportFragmentManager.beginTransaction()

            /*
            // We're passing FoodData in constructor, no need for Bundle
            val bundle = Bundle()
            bundle.putString("meal", mealsList[position].toString())

            foodUpdateFragment.arguments = bundle
             */

            Toast.makeText(holder.itemView.context, "Update " + mealsList[position].title?.uppercase(), Toast.LENGTH_SHORT).show()

            fragmentTransaction.replace(R.id.mainFrameLayout, foodUpdateFragment)
            //fragmentTransaction.addToBackStack(null) // go back
            fragmentTransaction.commit()

        }

        val builder = AlertDialog.Builder(holder.itemView.context)
        val positiveSpan = SpannableString("Yes").apply { setSpan(ForegroundColorSpan(Color.GREEN), 0, "Yes".length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE) }
        val negativeSpan = SpannableString("No").apply { setSpan(ForegroundColorSpan(Color.RED), 0, "No".length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE) }

        holder.deleteBtn.setOnClickListener {

            Toast.makeText(holder.itemView.context, "DELETE - " + mealsList[position].number, Toast.LENGTH_SHORT).show()

            builder.setMessage("Are you sure you want to delete " + mealsList[position].title)
                .setCancelable(true)
                .setTitle("Delete " + mealsList[position].title!!.uppercase())

                .setPositiveButton(positiveSpan) { dialog, id ->

                    Toast.makeText(holder.itemView.context, "DELETED - " + mealsList[position].title, Toast.LENGTH_SHORT).show()

                    foodViewModel.deleteFood(mealsList[position])
                    mealsList.removeAt(position)
                    notifyItemRemoved(position)
                }

                .setNegativeButton(negativeSpan) { dialog, id ->
                    dialog.dismiss()
                }
                .show()
        }

        holder.seeDetails.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Details for - " + mealsList[position].title, Toast.LENGTH_SHORT).show()
        }

    }

    override fun getItemCount(): Int {
        return mealsList.size
    }

    public fun setCurrentData(mealsList: List<FoodData>) {

        for(meal in mealsList){
            if (meal.date == CurrentDate().getCurrentData())
                this.mealsList.add(meal)
        }

        notifyDataSetChanged()
    }

    public fun setData(mealsList: List<FoodData>) {

        this.mealsList = mealsList as ArrayList<FoodData>
        notifyDataSetChanged()
    }
}