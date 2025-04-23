package com.example.myapplication

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import com.example.myapplication.fragments.AccountFragment
import com.example.myapplication.fragments.FoodAddFragment
import com.example.myapplication.fragments.FoodFragment
import com.example.myapplication.fragments.MainFragment
import com.example.myapplication.fragments.MealFragment
import com.example.myapplication.fragments.SettingsPopUpFragment
import com.example.myapplication.fragments.WorkoutFragment
import com.example.myapplication.utils.CurrentDate
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivityFragment : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var refreshImage: ImageView

    private fun setCurrentFragment(fragment: Fragment, icon: Int) {

        bottomNavigationView.menu.findItem(R.id.home).setIcon(R.drawable.home_outlined)
        bottomNavigationView.menu.findItem(R.id.foods).setIcon(R.drawable.calorie_outlined)
        bottomNavigationView.menu.findItem(R.id.account).setIcon(R.drawable.account_outlined)
        bottomNavigationView.menu.findItem(R.id.workout).setIcon(R.drawable.sport_outlined)
        bottomNavigationView.menu.findItem(R.id.meals).setIcon(R.drawable.meals_outlined)

        when(icon) {
            R.id.home -> bottomNavigationView.menu.findItem(R.id.home).setIcon(R.drawable.home_filled)
            R.id.foods -> bottomNavigationView.menu.findItem(R.id.foods).setIcon(R.drawable.calorie_filled)
            R.id.account -> bottomNavigationView.menu.findItem(R.id.account).setIcon(R.drawable.account_filled)
            R.id.meals -> bottomNavigationView.menu.findItem(R.id.meals).setIcon(R.drawable.meals_filled)
            R.id.workout -> bottomNavigationView.menu.findItem(R.id.workout).setIcon(R.drawable.sport_filled)

            else -> { }
        }

        supportFragmentManager.beginTransaction().apply {
            if(fragment is FoodAddFragment){
                addToBackStack(null)
            }
            replace(R.id.mainFrameLayout, fragment)
            commit()
        }
    }

    private fun setDateTitle() {

        val currentData = findViewById<TextView>(R.id.date)
        currentData.text = "TODAY: " + CurrentDate().getCurrentDate() + " - " + CurrentDate().getCurrentDay()
    }

    private fun refreshFragment() {

        refreshImage.setOnClickListener {

            val currentFragment: Fragment? = supportFragmentManager.findFragmentById(R.id.mainFrameLayout)

            when(currentFragment) {

                is MainFragment -> {
                    Toast.makeText(applicationContext, "Home Refreshed!", Toast.LENGTH_SHORT).show()
                    setCurrentFragment(MainFragment(), R.id.home)
                }

                is FoodFragment -> {
                    Toast.makeText(applicationContext, "Foods Refreshed!", Toast.LENGTH_SHORT).show()
                    setCurrentFragment(FoodFragment(), R.id.foods)
                }

                is AccountFragment -> {
                    Toast.makeText(applicationContext, "Account Refreshed!", Toast.LENGTH_SHORT).show()
                    setCurrentFragment(AccountFragment(), R.id.account)
                }

                is MealFragment -> {
                    Toast.makeText(applicationContext, "Meals Refreshed!", Toast.LENGTH_SHORT).show()
                    setCurrentFragment(MealFragment(), R.id.meals)
                }

                is WorkoutFragment -> {
                    Toast.makeText(applicationContext, "Workout Refreshed!", Toast.LENGTH_SHORT).show()
                    setCurrentFragment(WorkoutFragment(), R.id.workout)
                }

                else -> {
                    Toast.makeText(applicationContext, "Couldn't Refresh!", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Loading Screen must go before setContentView
        setTheme(R.style.Theme_MyApplication)

        setContentView(R.layout.activity_main_fragment)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        refreshImage = findViewById(R.id.swipeRefresh)

        setDateTitle()

        refreshFragment()

        val mainFragment = MainFragment()
        val foodFragment = FoodFragment()
        val mealFragment = MealFragment()
        val workoutFragment = WorkoutFragment()
        val accountFragment = AccountFragment()
        val foodAddFragment = FoodAddFragment()

        val addBtn: ImageButton = findViewById(R.id.addBtn)
        addBtn.isVisible = true

        val settings: ImageButton = findViewById(R.id.settings)

        setCurrentFragment(mainFragment, R.id.home)

        bottomNavigationView.setOnItemSelectedListener {
            addBtn.isVisible = true
            settings.setImageResource(R.drawable.settings_outlined)
            when(it.itemId){

                R.id.home -> {
                    setCurrentFragment(mainFragment, R.id.home)
                }

                R.id.foods -> {
                    setCurrentFragment(foodFragment, R.id.foods)
                }

                R.id.meals -> {
                    setCurrentFragment(mealFragment, R.id.meals)
                }

                R.id.workout -> {
                    setCurrentFragment(workoutFragment, R.id.workout)
                }

                R.id.account -> {
                    addBtn.isVisible = false
                    setCurrentFragment(accountFragment, R.id.account)
                }
            }
            true
        }

        addBtn.setOnClickListener {
            bottomNavigationView.selectedItemId = R.id.foods
            setCurrentFragment(foodAddFragment, R.id.foods)
            Toast.makeText(applicationContext, "Add New Food", Toast.LENGTH_SHORT).show()
            addBtn.isVisible = false
        }

        settings.setOnClickListener {

            settings.setImageResource(R.drawable.settings_filled)

            Toast.makeText(applicationContext, "Settings: Delete Databases...", Toast.LENGTH_SHORT).show()

            val settingsPopUp = SettingsPopUpFragment()
            settingsPopUp.show(supportFragmentManager, "settingsPopUp")
        }
    }
}