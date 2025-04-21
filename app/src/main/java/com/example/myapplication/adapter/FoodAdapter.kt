package com.example.myapplication.adapter

import android.app.AlertDialog
import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.utils.CurrentDate
import com.example.myapplication.R
import com.example.myapplication.data.models.FoodData
import com.example.myapplication.data.viewmodel.FoodViewModel
import com.example.myapplication.fragments.FoodUpdateFragment

/*

    Recycler View Adapter

*/

class FoodAdapter (private var foodList: ArrayList<FoodData>, private val foodViewModel: FoodViewModel) : RecyclerView.Adapter<FoodAdapter.MyViewHolder>() {

    // individual element
    class MyViewHolder(foodView: View) : RecyclerView.ViewHolder(foodView) {

        val title: TextView = foodView.findViewById(R.id.foodTitle)
        val calories: TextView = foodView.findViewById(R.id.foodCaloriesValue)
        val proteins: TextView = foodView.findViewById(R.id.foodProteinsValue)
        val carbs: TextView = foodView.findViewById(R.id.foodCarbsValue)
        val fats: TextView = foodView.findViewById(R.id.foodFatsValue)
        val fibers: TextView = foodView.findViewById(R.id.foodFibersValue)
        val btnDetails: Button = foodView.findViewById(R.id.btnDetails)
        val btnDelete: Button = foodView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val foodView = LayoutInflater.from(parent.context).inflate(R.layout.food_item, parent, false)

        return MyViewHolder(foodView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.title.text = foodList[position].title?.uppercase() + " g/ml" + " - No." + foodList[position].number.toString()
        holder.calories.text = foodList[position].calories.toString() + "kcal"
        holder.proteins.text = foodList[position].proteins.toString() + "g"
        holder.carbs.text = foodList[position].carbs.toString() + "g"
        holder.fats.text = foodList[position].fats.toString() + "g"
        holder.fibers.text =  foodList[position].fibers.toString() + " g"

        // from adapter to fragment
        holder.itemView.setOnClickListener {

            val foodUpdateFragment: FoodUpdateFragment = FoodUpdateFragment(foodList[position])

            val fragmentTransaction = (holder.itemView.context as AppCompatActivity).supportFragmentManager.beginTransaction()

            /*
            // We're passing FoodData in constructor, no need for Bundle
            val bundle = Bundle()
            bundle.putString("food", foodList[position].toString())

            foodUpdateFragment.arguments = bundle
             */

            Toast.makeText(holder.itemView.context, "Update " + foodList[position].title?.uppercase(), Toast.LENGTH_SHORT).show()

            fragmentTransaction.replace(R.id.mainFrameLayout, foodUpdateFragment)
            fragmentTransaction.addToBackStack(null) // go back
            fragmentTransaction.commit()

        }

        val builder = AlertDialog.Builder(holder.itemView.context)
        val positiveSpan = SpannableString("Yes").apply { setSpan(ForegroundColorSpan(Color.GREEN), 0, "Yes".length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE) }
        val negativeSpan = SpannableString("No").apply { setSpan(ForegroundColorSpan(Color.RED), 0, "No".length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE) }

        holder.btnDelete.setOnClickListener {

            builder.setMessage("Are you sure you want to delete " + foodList[position].title)
                .setCancelable(true)
                .setTitle("Delete " + foodList[position].title!!.uppercase())

                .setPositiveButton(positiveSpan) { dialog, id ->

                    Toast.makeText(holder.itemView.context, "DELETED - " + foodList[position].title, Toast.LENGTH_SHORT).show()

                    foodViewModel.deleteFood(foodList[position])

                    foodViewModel.readAllData.observe(holder.itemView.context as AppCompatActivity) { foods ->
                        foodList = foods as ArrayList<FoodData>
                    }

                    // Doesn't work sometimes
                    // foodList.removeAt(position)

                    notifyItemRemoved(position)
                    notifyItemChanged(position)
                }

                .setNegativeButton(negativeSpan) { dialog, id ->
                    dialog.dismiss()
                }
                .show()
        }

        holder.btnDetails.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Details for - " + foodList[position].title, Toast.LENGTH_SHORT).show()
        }

    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    public fun setCurrentData(list: List<FoodData>) {

        for(food in list){
            if (food.date == CurrentDate().getCurrentDate())
                this.foodList.add(food)
        }

        notifyDataSetChanged()
    }

    public fun setData(list: List<FoodData>) {

        this.foodList = list as ArrayList<FoodData>
        notifyDataSetChanged()
    }
}