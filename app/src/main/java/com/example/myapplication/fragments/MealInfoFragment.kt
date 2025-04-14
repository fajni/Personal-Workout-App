package com.example.myapplication.fragments

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.data.models.MealData
import com.example.myapplication.data.viewmodel.MealViewModel

class MealInfoFragment(private var meal: MealData) : Fragment() {

    private lateinit var mealViewMealData: MealViewModel

    private lateinit var infoMealTitle: TextView

    private lateinit var btnMealInfoClose: ImageButton

    private lateinit var btnMealInfoDelete: Button
    private lateinit var btnMealInfoUpdate: Button

    private lateinit var infoMealName: EditText
    private lateinit var infoMealTexture: Spinner
    private lateinit var infoMealIngredients: EditText
    private lateinit var infoMealDesc: EditText
    private lateinit var infoMealCalories: EditText
    private lateinit var infoMealProteins: EditText
    private lateinit var infoMealCarbs: EditText
    private lateinit var infoMealFats: EditText


    private fun initializeResources(view: View) {

        mealViewMealData = ViewModelProvider(this)[MealViewModel::class.java]

        infoMealTitle = view.findViewById(R.id.infoMealTitle)

        btnMealInfoClose = view.findViewById(R.id.btnMealInfoClose)

        btnMealInfoDelete = view.findViewById(R.id.btnMealInfoDelete)
        btnMealInfoUpdate = view.findViewById(R.id.btnMealInfoUpdate)

        infoMealName = view.findViewById(R.id.infoMealName)
        infoMealTexture = view.findViewById(R.id.infoMealTexture)
        infoMealIngredients = view.findViewById(R.id.infoMealIngredients)
        infoMealDesc = view.findViewById(R.id.infoMealDesc)
        infoMealCalories = view.findViewById(R.id.infoMealCalories)
        infoMealProteins = view.findViewById(R.id.infoMealProteins)
        infoMealCarbs = view.findViewById(R.id.infoMealCarbs)
        infoMealFats = view.findViewById(R.id.infoMealFats)

        val textures = listOf("Drink", "Food")
        val adapter = ArrayAdapter(requireContext(), R.layout.item_dropdown, textures)

        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        infoMealTexture.adapter = adapter
    }

    private fun setData() {

        infoMealTitle.setText("Info for " + meal.name.uppercase())

        infoMealName.setText(meal.name)
        infoMealTexture.setSelection(0)
        if(meal.texture.equals("Food"))
            infoMealTexture.setSelection(1)
        infoMealIngredients.setText(meal.ingredients)
        infoMealDesc.setText(meal.description)
        infoMealCalories.setText(meal.calories.toString())
        infoMealProteins.setText(meal.proteins.toString())
        infoMealCarbs.setText(meal.carbs.toString())
        infoMealFats.setText(meal.fats.toString())
    }

    private fun setButtons() {

        val builder = AlertDialog.Builder(context)
        val positiveSpan = SpannableString("Yes").apply { setSpan(ForegroundColorSpan(Color.GREEN), 0, "Yes".length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE) }
        val negativeSpan = SpannableString("No").apply { setSpan(ForegroundColorSpan(Color.RED), 0, "No".length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE) }

        btnMealInfoClose.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.mainFrameLayout, MealFragment())
                .commit()
        }

        btnMealInfoDelete.setOnClickListener {

            Toast.makeText(context, "DELETE - " + meal.name, Toast.LENGTH_SHORT).show()

            builder.setMessage("Are you sure you want to delete " + meal.name.uppercase())
                .setCancelable(true)
                .setTitle("Delete " + meal.name.uppercase())

                .setPositiveButton(positiveSpan) { dialog, id ->

                    try {

                        mealViewMealData.deleteMeal(meal)
                        Toast.makeText(context, "DELETED - " + meal.name, Toast.LENGTH_SHORT).show()

                        parentFragmentManager.beginTransaction()
                            .replace(R.id.mainFrameLayout, MealFragment())
                            .commit()

                    } catch (e: Exception) {
                        Toast.makeText(context, "ERROR - " + e.message, Toast.LENGTH_SHORT).show()
                        println(e.message)
                    }

                }

                .setNegativeButton(negativeSpan) { dialog, id ->
                    dialog.dismiss()
                }
                .show()

        }

        btnMealInfoUpdate.setOnClickListener {

            if(infoMealName.text.isBlank() ||
                infoMealTexture.selectedItem.toString().isBlank() ||
                infoMealIngredients.text.isBlank() ||
                infoMealCalories.text.isBlank() ||
                infoMealProteins.text.isBlank() ||
                infoMealCarbs.text.isBlank() ||
                infoMealFats.text.isBlank()
                ){

                Toast.makeText(context, "No empty fields allowed! ", Toast.LENGTH_SHORT).show()

            }
            else {

                try{

                    val updateMeal = MealData(
                        id = meal.id,
                        name = infoMealName.text.toString(),
                        texture = infoMealTexture.selectedItem.toString(),
                        ingredients = infoMealIngredients.text.toString(),
                        description = infoMealDesc.text.toString(),
                        calories = infoMealCalories.text.toString().toInt(),
                        proteins = infoMealProteins.text.toString().toInt(),
                        carbs = infoMealCarbs.text.toString().toInt(),
                        fats = infoMealFats.text.toString().toInt()
                    )

                    mealViewMealData.update(updateMeal)

                    parentFragmentManager.beginTransaction()
                        .replace(R.id.mainFrameLayout, MealFragment())
                        .commit()

                } catch (e: Exception) {
                    Toast.makeText(context, "ERROR - " + e.message, Toast.LENGTH_SHORT).show()
                    println(e.message)
                }
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_meal_info, container, false)

        initializeResources(view)

        setData()

        setButtons()

        return view
    }

}