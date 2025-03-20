package com.example.myapplication.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.data.models.FoodData

/*

    DATA ACCESS OBJECT.

    Contains the methods used for accessing the database.

*/

@Dao
interface FoodDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE) // if you add the same food, conflict won't appear
    suspend fun addFood(food: FoodData)

    @Query("SELECT * FROM food_data ORDER BY number ASC")
    fun readAllData(): LiveData<List<FoodData>>

    @Delete
    suspend fun deleteFood(food: FoodData)

    @Query("DELETE FROM food_data")
    suspend fun deleteAll()

    @Update
    suspend fun updateFood(food: FoodData)
}