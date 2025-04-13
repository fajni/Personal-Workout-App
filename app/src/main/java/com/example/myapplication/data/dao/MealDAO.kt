package com.example.myapplication.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.data.models.MealData

/*

    DATA ACCESS OBJECT.

    Contains the methods used for accessing the database.

*/

@Dao
interface MealDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMeal(meal: MealData)

    @Query("SELECT * FROM meal_data ORDER BY id ASC")
    fun readAllMeals(): LiveData<List<MealData>>

    @Delete
    suspend fun deleteMeal(meal: MealData)

    @Query("DELETE FROM meal_data")
    suspend fun deleteAllMeals()

    @Update
    suspend fun updateMeal(meal: MealData)

}