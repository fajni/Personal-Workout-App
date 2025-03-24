package com.example.myapplication.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.database.AccountDatabase
import com.example.myapplication.data.models.AccountData
import com.example.myapplication.data.repository.AccountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*

    The ViewModel's role is to provide data to the UI and survive
    configuration changes.
    A ViewModel acts as a communication center between the Repository and UI.

    From here, you're accessing all queries from DAO.

*/

class AccountViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AccountRepository
    public val readAccount: LiveData<AccountData?>

    init {
        val accountDAO = AccountDatabase.getDatabase(application).accountDAO()

        repository = AccountRepository(accountDAO)
        readAccount = repository.readAccount
    }

    fun addAccount(account: AccountData) {

        // coroutine. Adding will be in another (background) thread
        viewModelScope.launch(Dispatchers.IO) {
            repository.addAccount(account)
        }
    }

    fun deleteAccount(account: AccountData) {

        // coroutine. Adding will be in another (background) thread
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAccount(account)
        }
    }

    fun updateAccount(account: AccountData) {

        // coroutine. Adding will be in another (background) thread
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateAccount(account)
        }
    }

}