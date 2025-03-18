package com.example.myapplication

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication.fragments.AccountFragment
import com.example.myapplication.fragments.AddFragment
import com.example.myapplication.fragments.FoodFragment
import com.example.myapplication.fragments.HistoryFragment
import com.example.myapplication.fragments.MainFragment
import com.example.myapplication.fragments.WorkoutFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

import java.text.SimpleDateFormat
import java.util.Calendar

class MainActivityFragment : AppCompatActivity() {

    private fun setCurrentFragment(fragment: Fragment) {

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.mainFrameLayout, fragment)
            commit()
        }
    }

    private fun setDataTitle() {

        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        val current = formatter.format(time)

        val currentData = findViewById<TextView>(R.id.date)
        currentData.text = current.toString()
    }

    private fun setInitialIcons(bottomNavigationView: BottomNavigationView) {

        bottomNavigationView.menu.findItem(R.id.home).setIcon(R.drawable.home_outlined)
        bottomNavigationView.menu.findItem(R.id.food).setIcon(R.drawable.calorie_outlined)
        bottomNavigationView.menu.findItem(R.id.account).setIcon(R.drawable.account_outlined)
        bottomNavigationView.menu.findItem(R.id.workout).setIcon(R.drawable.dumbell_outlined)
        bottomNavigationView.menu.findItem(R.id.history).setIcon(R.drawable.history_outlined)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main_fragment)

        setDataTitle()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)

        val mainFragment = MainFragment()
        val foodFragment = FoodFragment()
        val historyFragment = HistoryFragment()
        val workoutFragment = WorkoutFragment()
        val accountFragment = AccountFragment()
        val addFragment = AddFragment()

        val addBtn: ImageButton = findViewById(R.id.addBtn)

        setCurrentFragment(mainFragment)

        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){

                R.id.home -> {
                    setCurrentFragment(mainFragment)
                    setInitialIcons(bottomNavigationView)
                    bottomNavigationView.menu.findItem(R.id.home).setIcon(R.drawable.home_filled)
                }

                R.id.food -> {
                    setCurrentFragment(foodFragment)
                    setInitialIcons(bottomNavigationView)
                    bottomNavigationView.menu.findItem(R.id.food).setIcon(R.drawable.calorie_filled)
                }

                R.id.history -> {
                    setCurrentFragment(historyFragment)
                    setInitialIcons(bottomNavigationView)
                    bottomNavigationView.menu.findItem(R.id.history).setIcon(R.drawable.history_filled)
                }

                R.id.workout -> {
                    setCurrentFragment(workoutFragment)
                    setInitialIcons(bottomNavigationView)
                    bottomNavigationView.menu.findItem(R.id.workout).setIcon(R.drawable.dumbell_filled)
                }

                R.id.account -> {
                    setCurrentFragment(accountFragment)
                    setInitialIcons(bottomNavigationView)
                    bottomNavigationView.menu.findItem(R.id.account).setIcon(R.drawable.account_filled)
                }
            }
            true
        }

        addBtn.setOnClickListener {
            bottomNavigationView.selectedItemId = R.id.food
            setCurrentFragment(addFragment)
            Toast.makeText(applicationContext, "Add New Meal/Food", Toast.LENGTH_SHORT).show()
        }
    }
}