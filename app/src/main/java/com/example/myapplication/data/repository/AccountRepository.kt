package com.example.myapplication.data.repository

import androidx.lifecycle.LiveData
import com.example.myapplication.data.dao.AccountDAO
import com.example.myapplication.data.models.AccountData

/*

    A repository class abstracts access to multiple data sources.

*/

class AccountRepository(private val accountDAO: AccountDAO) {

    val readAccount: LiveData<AccountData?> = accountDAO.readAccount()

    suspend fun addAccount(account: AccountData) {

        accountDAO.addAccount(account)
    }

    suspend fun deleteAccount(account: AccountData) {

        accountDAO.deleteAccount(account)
    }

    suspend fun updateAccount(account: AccountData) {

        accountDAO.updateAccount(account)
    }

}