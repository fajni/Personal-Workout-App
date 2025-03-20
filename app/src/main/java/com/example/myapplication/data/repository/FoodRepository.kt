package com.example.myapplication.data.repository

import androidx.lifecycle.LiveData
import com.example.myapplication.data.dao.FoodDAO
import com.example.myapplication.data.models.FoodData

/*

    A repository class abstracts access to multiple data sources.

*/

class FoodRepository(private val foodDAO: FoodDAO) {

    val readAllData: LiveData<List<FoodData>> = foodDAO.readAllData()

    suspend fun addFood(foodData: FoodData) {

        foodDAO.addFood(foodData)
    }

    suspend fun deleteFood(foodData: FoodData) {

        foodDAO.deleteFood(foodData)
    }

    suspend fun deleteAll() {

        foodDAO.deleteAll()
    }

    suspend fun updateFood(updatedFood: FoodData) {

        foodDAO.updateFood(updatedFood)
    }

}