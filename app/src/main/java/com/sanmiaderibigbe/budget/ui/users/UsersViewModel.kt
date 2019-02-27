package com.sanmiaderibigbe.budget.ui.users

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.sanmiaderibigbe.budget.data.Repository
import com.sanmiaderibigbe.budget.data.model.User
import com.sanmiaderibigbe.budget.data.model.UserWithTransactions

class UsersViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: Repository = Repository.getRepository(application, false)

    fun createUser(name: String, amount: String){
        repository.createNewUser(User(0, name, amount.toFloat()))
    }

    fun getUser(): LiveData<List<User>> {
        return repository.getUsers()
    }

    fun getUserWithTransactionInformation(): LiveData<List<UserWithTransactions>> {
        return repository.getUsersAndTheirTransactionData()
    }
}