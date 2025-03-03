package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication.fragments.AccountFragment
import com.example.myapplication.fragments.CaloriesFragment
import com.example.myapplication.fragments.HistoryFragment
import com.example.myapplication.fragments.MainFragment
import com.example.myapplication.fragments.WorkoutFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivityFragment : AppCompatActivity() {

    private fun setCurrentFragment(fragment: Fragment) {

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.mainFrameLayout, fragment)
            commit()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main_fragment)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)

        val mainFragment = MainFragment()
        val caloriesFragment = CaloriesFragment()
        val historyFragment = HistoryFragment()
        val workoutFragment = WorkoutFragment()
        val accountFragment = AccountFragment()

        setCurrentFragment(mainFragment)

        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> setCurrentFragment(mainFragment)
                R.id.calories -> setCurrentFragment(caloriesFragment)
                R.id.history -> setCurrentFragment(historyFragment)
                R.id.workout -> setCurrentFragment(workoutFragment)
                R.id.account -> setCurrentFragment(accountFragment)
            }
            true
        }
    }
}