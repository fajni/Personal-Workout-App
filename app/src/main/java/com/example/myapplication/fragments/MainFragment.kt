package com.example.myapplication.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.utils.CurrentDate
import com.example.myapplication.R
import com.example.myapplication.data.models.AccountData
import com.example.myapplication.data.viewmodel.AccountViewModel
import com.example.myapplication.data.viewmodel.FoodViewModel
import com.example.myapplication.data.viewmodel.WorkoutViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

    private lateinit var checkYourMacrosText: TextView
    private lateinit var noAccountValuesText: LinearLayout
    private lateinit var noValuesText: LinearLayout
    private lateinit var foodValues: ScrollView
    private lateinit var noWorkoutValuesText: LinearLayout
    private lateinit var todayWorkout: LinearLayout

    private lateinit var slideInImage: ImageView

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

        checkYourMacrosText = view.findViewById(R.id.checkYourMacrosText)
        noAccountValuesText = view.findViewById(R.id.noAccountValuesText)
        noValuesText = view.findViewById(R.id.noValuesText)
        noWorkoutValuesText = view.findViewById(R.id.noWorkoutValuesText)
        todayWorkout = view.findViewById(R.id.todayWorkoutValues)
        foodValues = view.findViewById(R.id.foodValues)

        slideInImage = view.findViewById(R.id.slideInImage)
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

        foodViewModel.readAllData.observe(viewLifecycleOwner, Observer { foods ->

            var noValues: Boolean = true

            val currentDate: String = CurrentDate().getCurrentDate()

            for (food in foods) {
                if (food.date == currentDate){
                    calories += food.calories!!
                    proteins += food.proteins!!
                    carbs += food.carbs!!
                    fats += food.fats!!

                    noValues = false
                }
            }

            if(noValues){
                noValuesText.isVisible = true
                foodValues.isVisible = false
                checkYourMacrosText.isVisible = false
            }

            updateProgressBars()
        })

        accountViewModel.readAccount.observe(viewLifecycleOwner, Observer { account ->

            if(account == null) {

                noAccountValuesText.isVisible = true
                foodValues.isVisible = false
                checkYourMacrosText.isVisible = false
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

            caloriesConsumed.setText(calories.toString() + " kcal")
            caloriesLeft.setText((myAccount?.calories!! - calories).toString() + " kcal")

            progressBarCalories.max = myAccount?.calories!!
            progressBarCalories.progress = calories
        }

        if (proteins != 0 && myAccount != null) {

            proteinsConsumed.setText(proteins.toString() + " g")
            proteinsLeft.setText((myAccount?.proteins!! - proteins).toString() + " g")

            progressBarProteins.max = myAccount?.proteins!!
            progressBarProteins.progress = proteins
        }

        if (carbs != 0 && myAccount != null) {

            carbsConsumed.setText(carbs.toString() + " g")
            carbsLeft.setText((myAccount?.carbs!! - carbs).toString() + " g")

            progressBarCarbs.max = myAccount?.carbs!!
            progressBarCarbs.progress = carbs
        }

        if (fats != 0 && myAccount != null) {

            fatsConsumed.setText(fats.toString() + " g")
            fatsLeft.setText((myAccount?.fats!! - fats).toString() + " g")

            progressBarFats.max = myAccount?.fats!!
            progressBarFats.progress = fats
        }

        calories = 0
        proteins = 0
        carbs = 0
        fats = 0
    }

    private fun rightSideSlideImage() {

        viewLifecycleOwner.lifecycleScope.launch {

            // Slide In
            delay(2000)
            slideInImage.visibility = View.VISIBLE
            val slideIn = AnimationUtils.loadAnimation(context, R.anim.slide_in)
            slideInImage.startAnimation(slideIn)

            // Slide out
            delay(3000)
            val slideOut = AnimationUtils.loadAnimation(context, R.anim.slide_out)
            slideInImage.startAnimation(slideOut)
            slideInImage.visibility = View.GONE

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        initializeResources(view)

        rightSideSlideImage()

        setData()

        return view
    }
}