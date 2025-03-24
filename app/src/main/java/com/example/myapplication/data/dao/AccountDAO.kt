package com.example.myapplication.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.data.models.AccountData

/*

    DATA ACCESS OBJECT.

    Contains the methods used for accessing the database.

*/

@Dao
interface AccountDAO {

    @Query("SELECT * FROM account_data LIMIT 1")
    fun readAccount(): LiveData<AccountData?>

    @Insert(onConflict = OnConflictStrategy.IGNORE) // if you add the same account, conflict won't appear
    suspend fun addAccount(account: AccountData)

    @Delete
    suspend fun deleteAccount(account: AccountData)

    @Update
    suspend fun updateAccount(account: AccountData)

}