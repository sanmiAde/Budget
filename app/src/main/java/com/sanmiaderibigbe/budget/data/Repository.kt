package com.sanmiaderibigbe.budget.data

import android.app.Application
import com.sanmiaderibigbe.budget.data.doa.TransacationDao
import com.sanmiaderibigbe.budget.data.doa.UserDoa

class Repository(application: Application) {

    private val userDoa: UserDoa
    private val transactionDao: TransacationDao

    init {
        val db = AppDatabase.getDatabase(application, false)
        transactionDao = db.transactionDao()
        userDoa = db.userDao()
    }




    companion object {
        private const val TAG = "Respository"

        private var instance: Repository? = null
        @Synchronized
        fun getRepository(application: Application): Repository {
            if (instance == null) {
                instance = Repository(application)
            }
            return instance!!
        }
    }

}