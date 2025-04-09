package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.data.models.FoodData
import com.example.myapplication.data.viewmodel.FoodViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.text.SimpleDateFormat
import java.util.Calendar

class FoodAddFragment : Fragment() {

    private lateinit var foodViewModel: FoodViewModel

    private lateinit var title: EditText
    private lateinit var titleValue: EditText
    private lateinit var caloriesValue: EditText
    private lateinit var proteinsValue: EditText
    private lateinit var carbsValue: EditText
    private lateinit var fatsValue: EditText

    private lateinit var date: String

    private lateinit var submitBtn: Button

    private lateinit var closeBtn: ImageButton

    private fun checkBlankFields(): Boolean {

        if (
            title.text.isBlank() ||
            titleValue.text.isBlank() ||
            caloriesValue.text.isBlank() ||
            proteinsValue.text.isBlank() ||
            carbsValue.text.isBlank() ||
            fatsValue.text.isBlank()
        )
            return false

        return true
    }

    private fun clearFields() {
        title.setText("")
        titleValue.setText("")
        caloriesValue.setText("")
        proteinsValue.setText("")
        carbsValue.setText("")
        fatsValue.setText("")
    }

    private fun insertDataToDatabase(foodData: FoodData) {

        var toast: Toast

        try {

            foodViewModel.addFood(foodData)
            toast = Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_SHORT)

        } catch (e: Exception) {

            print(e.message)
            toast = Toast.makeText(requireContext(), "Error occurred!", Toast.LENGTH_SHORT)
        }

        toast.show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_add_food, container, false)

        foodViewModel = ViewModelProvider(this)[FoodViewModel::class.java]

        submitBtn = view.findViewById<Button>(R.id.submit)
        closeBtn = view.findViewById<ImageButton>(R.id.btnCloseAddFood)

        title = view.findViewById<EditText>(R.id.addTitle)
        titleValue = view.findViewById<EditText>(R.id.addTitleValue)
        caloriesValue = view.findViewById<EditText>(R.id.addCaloriesValue)
        proteinsValue = view.findViewById<EditText>(R.id.addProteinsValue)
        carbsValue = view.findViewById<EditText>(R.id.addCarbsValue)
        fatsValue = view.findViewById<EditText>(R.id.addFatsValue)

        val addValues = view.findViewById<TextView>(R.id.addValues)

        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        date = formatter.format(time)

        submitBtn.setOnClickListener {

            val submit: Boolean = checkBlankFields()

            if (submit) {

                // id = 0 -> Room library will do the id (primary key) numbering
                var data: FoodData = FoodData(
                    number = 0,
                    title = title.text.toString() + " " + titleValue.text.toString(),
                    calories = caloriesValue.text.toString().toInt(),
                    proteins = proteinsValue.text.toString().toInt(),
                    carbs = carbsValue.text.toString().toInt(),
                    fats = fatsValue.text.toString().toInt(),
                    date = date
                )

                addValues.text = data.toString()

                insertDataToDatabase(data)

                clearFields()

                parentFragmentManager.beginTransaction()
                    .replace(R.id.mainFrameLayout, FoodFragment())
                    .commit()

            } else {
                Toast.makeText(context, "EMPTY FIELDS ARE NOT ALLOWED", Toast.LENGTH_SHORT).show()
            }
        }

        closeBtn.setOnClickListener {

            requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView).selectedItemId = R.id.food

            parentFragmentManager.beginTransaction()
                .replace(R.id.mainFrameLayout, FoodFragment())
                .commit()
        }

        // Set ADD Button to VISIBLE
        val addBtn = requireActivity().findViewById<ImageButton>(R.id.addBtn)
        addBtn.isVisible = false

        return view
    }
}