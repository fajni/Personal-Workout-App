package com.example.myapplication.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.data.dao.AccountDAO
import com.example.myapplication.data.dao.FoodDAO
import com.example.myapplication.data.dao.MealDAO
import com.example.myapplication.data.dao.WorkoutDAO
import com.example.myapplication.data.models.*

/*

    DATABASE CONNECTION

    Contains the database holder and serves as the main
    access point for the underlying connection to your
    app's persisted, relational data.

*/

@Database(entities = [FoodData::class, AccountData::class, WorkoutData::class, MealData::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun foodDAO(): FoodDAO
    abstract fun accountDAO(): AccountDAO
    abstract fun workoutDAO(): WorkoutDAO
    abstract fun mealDAO(): MealDAO

    // companion object is visible to other objects
    /*
        AppDatabase will be Singleton class,
        Only 1 instance will be created
    */
    companion object {

        @Volatile // other threads can see the instance
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            val tempInstance = INSTANCE

            if(tempInstance != null)
                return tempInstance

            synchronized(this) {
                val dbInstance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "AppDatabase"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = dbInstance
                return dbInstance
            }
        }
    }
}