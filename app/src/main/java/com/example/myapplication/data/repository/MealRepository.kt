package com.example.myapplication.data.repository

import androidx.lifecycle.LiveData
import com.example.myapplication.data.dao.MealDAO
import com.example.myapplication.data.models.MealData

/*

    A repository class abstracts access to multiple data sources.

*/

class MealRepository(private val mealDAO: MealDAO) {

    val readAllMeals: LiveData<List<MealData>> = mealDAO.readAllMeals()

    suspend fun addMeal(meal: MealData) {

        mealDAO.addMeal(meal)
    }

    suspend fun deleteMeal(meal: MealData) {

        mealDAO.deleteMeal(meal)
    }

    suspend fun deleteAllMeals() {

        mealDAO.deleteAllMeals()
    }

    suspend fun updateMeal(meal: MealData) {

        mealDAO.updateMeal(meal)
    }

}