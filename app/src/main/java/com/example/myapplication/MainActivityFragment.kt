package com.example.myapplication

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.myapplication.fragments.AccountFragment
import com.example.myapplication.fragments.FoodAddFragment
import com.example.myapplication.fragments.FoodFragment
import com.example.myapplication.fragments.HistoryFragment
import com.example.myapplication.fragments.MainFragment
import com.example.myapplication.fragments.WorkoutFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivityFragment : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    private fun setCurrentFragment(fragment: Fragment, icon: Int) {

        bottomNavigationView.menu.findItem(R.id.home).setIcon(R.drawable.home_outlined)
        bottomNavigationView.menu.findItem(R.id.food).setIcon(R.drawable.calorie_outlined)
        bottomNavigationView.menu.findItem(R.id.account).setIcon(R.drawable.account_outlined)
        bottomNavigationView.menu.findItem(R.id.workout).setIcon(R.drawable.sport_outlined)
        bottomNavigationView.menu.findItem(R.id.history).setIcon(R.drawable.history_outlined)

        when(icon) {
            R.id.home -> bottomNavigationView.menu.findItem(R.id.home).setIcon(R.drawable.home_filled)
            R.id.food -> bottomNavigationView.menu.findItem(R.id.food).setIcon(R.drawable.calorie_filled)
            R.id.account -> bottomNavigationView.menu.findItem(R.id.account).setIcon(R.drawable.account_filled)
            R.id.history -> bottomNavigationView.menu.findItem(R.id.history).setIcon(R.drawable.history_filled)
            R.id.workout -> bottomNavigationView.menu.findItem(R.id.workout).setIcon(R.drawable.sport_filled)

            else -> { }
        }

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.mainFrameLayout, fragment)
            commit()
        }
    }

    private fun setDateTitle() {

        val currentData = findViewById<TextView>(R.id.date)
        currentData.text = "TODAY: " + CurrentDate().getCurrentData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main_fragment)

        setDateTitle()

        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        val mainFragment = MainFragment()
        val foodFragment = FoodFragment()
        val historyFragment = HistoryFragment()
        val workoutFragment = WorkoutFragment()
        val accountFragment = AccountFragment()
        val foodAddFragment = FoodAddFragment()

        val addBtn: ImageButton = findViewById(R.id.addBtn)
        addBtn.isVisible = true

        val accountBtn: ImageButton = findViewById(R.id.mainLinearLayoutTitleAccountBtn)

        setCurrentFragment(mainFragment, R.id.home)

        bottomNavigationView.setOnItemSelectedListener {
            addBtn.isVisible = true
            when(it.itemId){

                R.id.home -> {
                    setCurrentFragment(mainFragment, R.id.home)
                }

                R.id.food -> {
                    setCurrentFragment(foodFragment, R.id.food)
                }

                R.id.history -> {
                    setCurrentFragment(historyFragment, R.id.history)
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
            bottomNavigationView.selectedItemId = R.id.food
            setCurrentFragment(foodAddFragment, R.id.food)
            Toast.makeText(applicationContext, "Add New Meal/Food", Toast.LENGTH_SHORT).show()
            addBtn.isVisible = false
        }

        accountBtn.setOnClickListener {
            bottomNavigationView.selectedItemId = R.id.account
            setCurrentFragment(accountFragment, R.id.account)
            addBtn.isVisible = false
        }
    }
}