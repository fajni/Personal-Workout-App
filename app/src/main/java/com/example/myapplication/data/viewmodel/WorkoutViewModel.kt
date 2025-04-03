package com.example.myapplication.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.database.WorkoutDatabase
import com.example.myapplication.data.models.WorkoutData
import com.example.myapplication.data.repository.WorkoutRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*

    The ViewModel's role is to provide data to the UI and survive
    configuration changes.
    A ViewModel acts as a communication center between the Repository and UI.

    From here, you're accessing all queries from DAO.

*/

class WorkoutViewModel(application: Application): AndroidViewModel(application) {

    private val repository: WorkoutRepository
    public val readWorkouts: LiveData<List<WorkoutData>>

    // init block will always execute first when user ViewModel is called!
    init {

        val workoutDao = WorkoutDatabase.getDatabase(application).workoutDao()
        repository = WorkoutRepository(workoutDao)
        readWorkouts = repository.readWorkouts
    }

    fun readWorkoutsDay(day: String): LiveData<List<WorkoutData>> {

        return repository.readWorkoutsDay(day)
    }

    fun addWorkout(workout: WorkoutData) {

        workout.day.lowercase()

        viewModelScope.launch(Dispatchers.IO) {
            repository.addWorkout(workout)
        }
    }

    fun deleteWorkout(workout: WorkoutData) {

        workout.day.lowercase()

        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteWorkout(workout)
        }
    }

    fun deleteWorkouts() {

        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteWorkouts()
        }
    }

    fun updateWorkout(workout: WorkoutData) {

        workout.day.lowercase()

        viewModelScope.launch(Dispatchers.IO) {
            repository.updateWorkout(workout)
        }
    }
}