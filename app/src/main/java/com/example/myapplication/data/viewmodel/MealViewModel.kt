package com.example.myapplication.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.database.AppDatabase
import com.example.myapplication.data.models.MealData
import com.example.myapplication.data.repository.MealRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*

    The ViewModel's role is to provide data to the UI and survive
    configuration changes.
    A ViewModel acts as a communication center between the Repository and UI.

    From here, you're accessing all queries from DAO.

*/

class MealViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MealRepository
    public val readAllMeals: LiveData<List<MealData>>

    // init block will always execute first when user ViewModel is called!
    init {

        val mealDAO = AppDatabase.getDatabase(application).mealDAO()
        repository = MealRepository(mealDAO)
        readAllMeals = repository.readAllMeals
    }

    fun addMeal(meal: MealData) {

        // coroutine. Adding will be in another (background) thread
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMeal(meal)
        }
    }

    fun deleteMeal(meal: MealData) {

        // coroutine. Adding will be in another (background) thread
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteMeal(meal)
        }
    }

    fun deleteAll() {

        // coroutine. Adding will be in another (background) thread
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllMeals()
        }
    }

    fun update(meal: MealData) {

        // coroutine. Adding will be in another (background) thread
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateMeal(meal)
        }
    }

}