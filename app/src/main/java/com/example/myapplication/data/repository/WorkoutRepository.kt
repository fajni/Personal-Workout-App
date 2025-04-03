package com.example.myapplication.data.repository

import androidx.lifecycle.LiveData
import com.example.myapplication.data.dao.WorkoutDAO
import com.example.myapplication.data.models.WorkoutData

/*

    A repository class abstracts access to multiple data sources.

*/

class WorkoutRepository(private val workoutDAO: WorkoutDAO) {

    val readWorkouts: LiveData<List<WorkoutData>> = workoutDAO.readWorkouts()

    fun readWorkoutsDay(day: String): LiveData<List<WorkoutData>> {

        return workoutDAO.readWorkoutDay(day)
    }

    suspend fun addWorkout(workout: WorkoutData) {

        workoutDAO.addWorkout(workout)
    }

    suspend fun deleteWorkout(workout: WorkoutData) {

        workoutDAO.deleteWorkout(workout)
    }

    suspend fun deleteWorkouts() {

        workoutDAO.deleteWorkouts()
    }

    suspend fun updateWorkout(workout: WorkoutData) {

        workoutDAO.updateWorkout(workout)
    }

}