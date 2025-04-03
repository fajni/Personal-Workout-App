package com.example.myapplication.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.data.models.WorkoutData

/*

    DATA ACCESS OBJECT.

    Contains the methods used for accessing the database.

*/

@Dao
interface WorkoutDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE) // if you add the same food, conflict won't appear
    suspend fun addWorkout(workout: WorkoutData)

    @Delete
    suspend fun deleteWorkout(workout: WorkoutData)

    @Query("DELETE FROM workout_data")
    suspend fun deleteWorkouts()

    @Update
    suspend fun updateWorkout(workout: WorkoutData)

    @Query("SELECT * FROM workout_data")
    fun readWorkouts(): LiveData<List<WorkoutData>>

    @Query("SELECT * FROM workout_data WHERE day = :day")
    fun readWorkoutDay(day: String): LiveData<List<WorkoutData>>
}