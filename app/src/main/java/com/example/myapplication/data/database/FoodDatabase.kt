package com.example.myapplication.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.data.dao.FoodDAO
import com.example.myapplication.data.models.FoodData

/*

    DATABASE CONNECTION

    Contains the database holder and serves as the main
    access point for the underlying connection to your
    app's persisted, relational data.

*/

@Database(entities = [FoodData::class], version = 1, exportSchema = false)
abstract class FoodDatabase : RoomDatabase() {

    abstract fun foodDao(): FoodDAO

    // companion object is visible to other objects
    /*
        FoodDatabase will be Singleton class,
        Only 1 instance will be created
    */
    companion object{

        @Volatile // other threads can see the instance
        private var INSTANCE: FoodDatabase? = null

        fun getDatabase(context: Context): FoodDatabase {

            val tempInstance = INSTANCE

            if(tempInstance != null)
                return tempInstance

            synchronized(this){
                val dbInstance = Room.databaseBuilder(
                    context.applicationContext,
                    FoodDatabase::class.java,
                    "FoodDatabase"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = dbInstance
                return dbInstance
            }
        }
    }

}