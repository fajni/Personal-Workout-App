package com.example.myapplication.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.database.FoodDatabase
import com.example.myapplication.data.models.FoodData
import com.example.myapplication.data.repository.FoodRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*

    The ViewModel's role is to provide data to the UI and survive
    configuration changes.
    A ViewModel acts as a communication center between the Repository and UI.

    From here, you're accessing all queries from DAO.

*/

class FoodViewModel(application: Application): AndroidViewModel(application) {

    public val readAllData: LiveData<List<FoodData>>
    private val repository: FoodRepository

    // init block will always execute first when user ViewModel is called!
    init {

        val foodDAO = FoodDatabase.getDatabase(application).foodDao()
        repository = FoodRepository(foodDAO)
        readAllData = repository.readAllData
    }

    fun addFood(food: FoodData) {

        // coroutine. Adding will be in another (background) thread
        viewModelScope.launch(Dispatchers.IO) {
            repository.addFood(food)
        }

    }

    fun deleteFood(food: FoodData) {

        // coroutine. Adding will be in another (background) thread
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFood(food)
        }
    }

    fun deleteAll() {

        // coroutine. Adding will be in another (background) thread
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }

}