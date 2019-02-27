package com.sanmiaderibigbe.budget.data

import android.app.Application
import android.arch.lifecycle.LiveData
import com.sanmiaderibigbe.budget.data.doa.TransacationDao
import com.sanmiaderibigbe.budget.data.doa.UserDoa
import com.sanmiaderibigbe.budget.data.model.Transaction
import com.sanmiaderibigbe.budget.data.model.TransactionType
import com.sanmiaderibigbe.budget.data.model.User
import com.sanmiaderibigbe.budget.data.model.UserWithTransactions
import org.jetbrains.anko.doAsync

class Repository(application: Application, memoryOnlyDatbase: Boolean) {

    private val userDoa: UserDoa
    private val transactionDao: TransacationDao

    init {
        val db = AppDatabase.getDatabase(application, false)
        transactionDao = db.transactionDao()
        userDoa = db.userDao()
    }

    fun createNewUser(user : User) {
       doAsync { userDoa.insert(user) }
    }

    fun deleteAllForTestingPurposes(){
        userDoa.deleteAll()
        transactionDao.deleteAll()
    }

    fun getUsers() : LiveData<List<User>> = userDoa.getUser()

    fun getUsersAndTheirTransactionData(): LiveData<List<UserWithTransactions>> {
        return userDoa.getAllBooksWithNotes()
    }

    fun createTransaction(transaction: Transaction) {
        doAsync {
            transactionDao.insert(transaction)
        }

    }

    fun getTransactionsByUserId(userId: Long): LiveData<List<Transaction>> = transactionDao.getTransactionByUser(userId)


    companion object {
        private const val TAG = "Respository"

        private var instance: Repository? = null
        @Synchronized
        fun getRepository(application: Application, memoryOnlyDatbase: Boolean): Repository {
            if (instance == null) {
                instance = Repository(application, memoryOnlyDatbase )
            }
            return instance!!
        }
    }

}