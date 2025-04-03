package com.example.myapplication.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.data.dao.WorkoutDAO
import com.example.myapplication.data.models.WorkoutData

/*

    DATABASE CONNECTION

    Contains the database holder and serves as the main
    access point for the underlying connection to your
    app's persisted, relational data.

*/

@Database(entities = [WorkoutData::class], version = 1, exportSchema = false)
abstract class WorkoutDatabase : RoomDatabase() {

    abstract fun workoutDao(): WorkoutDAO

    // companion object is visible to other objects
    /*
        FoodDatabase will be Singleton class,
        Only 1 instance will be created
    */
    companion object {

        @Volatile // other threads can see the instance
        private var INSTANCE: WorkoutDatabase? = null

        fun getDatabase(context: Context): WorkoutDatabase {

            val tempInstance = INSTANCE

            if(tempInstance != null)
                return tempInstance

            synchronized(this) {
                val dbInstance = Room.databaseBuilder(
                    context.applicationContext,
                    WorkoutDatabase::class.java,
                    "WorkoutDatabase"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = dbInstance
                return dbInstance
            }
        }
    }

}