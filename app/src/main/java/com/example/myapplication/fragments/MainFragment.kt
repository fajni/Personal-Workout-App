package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.CurrentDate
import com.example.myapplication.R
import com.example.myapplication.data.models.AccountData
import com.example.myapplication.data.viewmodel.AccountViewModel
import com.example.myapplication.data.viewmodel.FoodViewModel

class MainFragment : Fragment() {

    private lateinit var foodViewModel: FoodViewModel
    private lateinit var accountViewModel: AccountViewModel

    private lateinit var progressBarCalories: ProgressBar
    private lateinit var progressBarProteins: ProgressBar
    private lateinit var progressBarCarbs: ProgressBar
    private lateinit var progressBarFats: ProgressBar

    private var calories: Int = 0
    private var proteins: Int = 0
    private var carbs: Int = 0
    private var fats: Int = 0

    private var myAccount: AccountData? = null

    private fun setData() {

        foodViewModel.readAllData.observe(viewLifecycleOwner, Observer { meals ->

            val currentDate: String = CurrentDate().getCurrentData()

            for (meal in meals) {
                if (meal.date == currentDate){
                    calories += meal.calories!!
                    proteins += meal.proteins!!
                    carbs += meal.carbs!!
                    fats += meal.fats!!
                }
            }

            //updateProgressBars()
        })

        accountViewModel.readAccount.observe(viewLifecycleOwner, Observer { account ->

            myAccount = account

            updateProgressBars()
        })

    }

    private fun updateProgressBars() {

        progressBarCalories.progress = 0
        progressBarProteins.progress = 0
        progressBarCarbs.progress = 0
        progressBarFats.progress = 0

        if (calories != 0 && myAccount != null) {
            progressBarCalories.max = myAccount?.calories!!
            progressBarCalories.progress = calories
        }

        if (proteins != 0 && myAccount != null) {
            progressBarProteins.max = myAccount?.proteins!!
            progressBarProteins.progress = proteins
        }

        if (carbs != 0 && myAccount != null) {
            progressBarCarbs.max = myAccount?.carbs!!
            progressBarCarbs.progress = carbs
        }

        if (fats != 0 && myAccount != null) {
            progressBarFats.max = myAccount?.fats!!
            progressBarFats.progress = fats
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        foodViewModel = ViewModelProvider(this)[FoodViewModel::class.java]
        accountViewModel = ViewModelProvider(this)[AccountViewModel::class.java]

        progressBarCalories = view.findViewById(R.id.progressBarCalories)
        progressBarProteins = view.findViewById(R.id.progressBarProteins)
        progressBarCarbs = view.findViewById(R.id.progressBarCarbs)
        progressBarFats = view.findViewById(R.id.progressBarFats)

        setData()

        return view
    }
}