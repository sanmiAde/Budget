package com.sanmiaderibigbe.budget.ui

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.sanmiaderibigbe.budget.data.Repository
import com.sanmiaderibigbe.budget.data.model.Transaction
import com.sanmiaderibigbe.budget.data.model.TransactionType
import java.util.*


class TransactionViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: Repository = Repository.getRepository(application, false)

    fun createTransaction(amount: String, transactionType: String, userId: Long){

        var transactionState : TransactionType? = null
        when (transactionType.toUpperCase()){
            "D" -> {
                transactionState = TransactionType.DEBIT
            }
            "C" -> {
                transactionState = TransactionType.CREDIT
            }
        }
        repository.createTransaction(Transaction(0, amount.toFloat(), Date(), transactionState!!,userId))

    }

    fun getTransaction(userId: Long): LiveData<List<Transaction>> {
        return  repository.getTransactionsByUserId(userId)
    }

//    fun getTransaction(): LiveData<List<Transaction>> {
//
//    }
}