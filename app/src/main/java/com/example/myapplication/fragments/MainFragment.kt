package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.utils.CurrentDate
import com.example.myapplication.R
import com.example.myapplication.data.models.AccountData
import com.example.myapplication.data.viewmodel.AccountViewModel
import com.example.myapplication.data.viewmodel.FoodViewModel
import com.example.myapplication.data.viewmodel.WorkoutViewModel

class MainFragment : Fragment() {

    private lateinit var foodViewModel: FoodViewModel
    private lateinit var accountViewModel: AccountViewModel
    private lateinit var workoutViewModel: WorkoutViewModel

    private lateinit var progressBarCalories: ProgressBar
    private lateinit var progressBarProteins: ProgressBar
    private lateinit var progressBarCarbs: ProgressBar
    private lateinit var progressBarFats: ProgressBar

    private lateinit var caloriesConsumed: TextView
    private lateinit var caloriesLeft: TextView
    private lateinit var proteinsConsumed: TextView
    private lateinit var proteinsLeft: TextView
    private lateinit var carbsConsumed: TextView
    private lateinit var carbsLeft: TextView
    private lateinit var fatsConsumed: TextView
    private lateinit var fatsLeft: TextView

    private var calories: Int = 0
    private var proteins: Int = 0
    private var carbs: Int = 0
    private var fats: Int = 0

    private var myAccount: AccountData? = null

    private lateinit var todayTraining: TextView
    private lateinit var todayMuscle: TextView

    private lateinit var noAccountValuesText: LinearLayout
    private lateinit var noValuesText: LinearLayout
    private lateinit var foodValues: LinearLayout
    private lateinit var noWorkoutValuesText: LinearLayout
    private lateinit var todayWorkout: LinearLayout

    private fun initializeResources(view: View) {

        foodViewModel = ViewModelProvider(this)[FoodViewModel::class.java]
        accountViewModel = ViewModelProvider(this)[AccountViewModel::class.java]
        workoutViewModel = ViewModelProvider(this)[WorkoutViewModel::class.java]

        progressBarCalories = view.findViewById(R.id.progressBarCalories)
        progressBarProteins = view.findViewById(R.id.progressBarProteins)
        progressBarCarbs = view.findViewById(R.id.progressBarCarbs)
        progressBarFats = view.findViewById(R.id.progressBarFats)

        caloriesConsumed = view.findViewById(R.id.caloriesConsumed)
        caloriesLeft = view.findViewById(R.id.caloriesLeft)
        proteinsConsumed = view.findViewById(R.id.proteinsConsumed)
        proteinsLeft = view.findViewById(R.id.proteinsLeft)
        carbsConsumed = view.findViewById(R.id.carbsConsumed)
        carbsLeft = view.findViewById(R.id.carbsLeft)
        fatsConsumed = view.findViewById(R.id.fatsConsumed)
        fatsLeft = view.findViewById(R.id.fatsLeft)

        todayTraining = view.findViewById(R.id.todayTrainingTitle)
        todayMuscle = view.findViewById(R.id.todayMusclePart)

        noAccountValuesText = view.findViewById(R.id.noAccountValuesText)
        noValuesText = view.findViewById(R.id.noValuesText)
        noWorkoutValuesText = view.findViewById(R.id.noWorkoutValuesText)
        todayWorkout = view.findViewById(R.id.todayWorkoutValues)
        foodValues = view.findViewById(R.id.foodValues)
    }


    private fun setData() {

        todayTraining.setText("")
        todayMuscle.setText("")

        workoutViewModel.readWorkouts.observe(viewLifecycleOwner, Observer { workouts ->

            if(workouts.isEmpty()){

                todayWorkout.isVisible = false
                noWorkoutValuesText.isVisible = true
            }

            val currentDay = CurrentDate().getCurrentDay().lowercase()

            for(workout in workouts) {
                if(workout.day == currentDay){
                    todayTraining.setText(workout.workoutTitle)
                    todayMuscle.setText(workout.muscle)
                }
            }

        })

        foodViewModel.readAllData.observe(viewLifecycleOwner, Observer { meals ->

            if(meals.isEmpty()) {

                noValuesText.isVisible = true
                foodValues.isVisible = false
            }

            val currentDate: String = CurrentDate().getCurrentData()

            for (meal in meals) {
                if (meal.date == currentDate){
                    calories += meal.calories!!
                    proteins += meal.proteins!!
                    carbs += meal.carbs!!
                    fats += meal.fats!!
                }
            }

            updateProgressBars()
        })

        accountViewModel.readAccount.observe(viewLifecycleOwner, Observer { account ->

            if(account == null) {

                noAccountValuesText.isVisible = true
                foodValues.isVisible = false
            }

            myAccount = account

            updateProgressBars()
        })

    }

    private fun updateProgressBars() {

        progressBarCalories.progress = 0
        progressBarCalories.max = 0
        progressBarProteins.progress = 0
        progressBarProteins.max = 0
        progressBarCarbs.progress = 0
        progressBarCarbs.max = 0
        progressBarFats.progress = 0
        progressBarFats.max = 0

        if (calories != 0 && myAccount != null) {
            progressBarCalories.max = myAccount?.calories!!
            progressBarCalories.progress = calories

            caloriesConsumed.setText(calories.toString() + " kcal")
            caloriesLeft.setText((myAccount?.calories!! - calories).toString() + " kcal")

            println("Calories Max: " + progressBarCalories.max)
            println("Calories Progress: " + progressBarCalories.progress)
        }

        if (proteins != 0 && myAccount != null) {
            progressBarProteins.max = myAccount?.proteins!!
            progressBarProteins.progress = proteins

            proteinsConsumed.setText(proteins.toString() + " g")
            proteinsLeft.setText((myAccount?.proteins!! - proteins).toString() + " g")

            println("Proteins Max: " + progressBarProteins.max)
            println("Proteins Progress: " + progressBarProteins.progress)
        }

        if (carbs != 0 && myAccount != null) {
            progressBarCarbs.max = myAccount?.carbs!!
            progressBarCarbs.progress = carbs

            carbsConsumed.setText(carbs.toString() + " g")
            carbsLeft.setText((myAccount?.carbs!! - carbs).toString() + " g")

            println("Carbs Max: " + progressBarCarbs.max)
            println("Carbs Progress: " + progressBarCarbs.progress)
        }

        if (fats != 0 && myAccount != null) {
            progressBarFats.max = myAccount?.fats!!
            progressBarFats.progress = fats

            fatsConsumed.setText(fats.toString() + " g")
            fatsLeft.setText((myAccount?.fats!! - fats).toString() + " g")

            println("Fats Max: " + progressBarFats.max)
            println("Fats Progress: " + progressBarFats.progress)
        }

        calories = 0
        proteins = 0
        carbs = 0
        fats = 0
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        initializeResources(view)

        setData()

        return view
    }
}