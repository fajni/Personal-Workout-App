package com.example.myapplication.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.data.dao.AccountDAO
import com.example.myapplication.data.models.AccountData

/*

    DATABASE CONNECTION

    Contains the database holder and serves as the main
    access point for the underlying connection to your
    app's persisted, relational data.

*/

@Database(entities = [AccountData::class], version = 1, exportSchema = false)
abstract class AccountDatabase : RoomDatabase(){

    abstract fun accountDAO(): AccountDAO

    // companion object is visible to other objects
    /*
        AccountDatabase will be Singleton class,
        Only 1 instance will be created
    */
    companion object {

        @Volatile // other threads can see the instance
        private var INSTANCE: AccountDatabase? = null

        fun getDatabase(context: Context): AccountDatabase {

            val tempInstance = INSTANCE

            if(tempInstance != null)
                return tempInstance

            synchronized(this) {
                val dbInstance = Room.databaseBuilder(
                    context.applicationContext,
                    AccountDatabase::class.java,
                    "AccountDatabase"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = dbInstance
                return dbInstance
            }

        }

    }

}