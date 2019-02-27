package com.sanmiaderibigbe.budget.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.support.test.runner.AndroidJUnit4
import org.junit.runner.RunWith
import android.support.test.InstrumentationRegistry
import com.sanmiaderibigbe.budget.data.doa.TransacationDao
import com.sanmiaderibigbe.budget.data.model.Transaction
import com.sanmiaderibigbe.budget.data.model.TransactionType
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit


@RunWith(AndroidJUnit4::class)
class TransactionTest {

    private lateinit var db: AppDatabase
    private lateinit var doa : TransacationDao

    @Before
    fun setUp() {
        db = AppDatabase.getDatabase(InstrumentationRegistry.getTargetContext(), true)
        doa = db.transactionDao()
        doa.deleteAll()
    }

    @Test
    fun insert_newTransaction_shouldBeInserted(){
        var currentListOfTransacation = doa.getTransaction().blockingObserve()?.size
        Assert.assertEquals(currentListOfTransacation, 0)
        val transaction = Transaction(
            0,
            1000f,
            Date(),
            TransactionType.CREDIT,
            0
        )
        doa.insert(transaction)
         currentListOfTransacation = doa.getTransaction().blockingObserve()?.size
        Assert.assertEquals(currentListOfTransacation, 1)
        doa.delete(transaction)
    }


    @Test
    fun delete_Transaction_shouldBeDeleted(){

        val transaction = Transaction(
            0,
            1000f,
            Date(),
            TransactionType.CREDIT,
            0
        )
        doa.insert(transaction)
        doa.delete(transaction)
        val currentListOfTransacation = doa.getTransaction().blockingObserve()?.size
        Assert.assertEquals(currentListOfTransacation, 1)
    }




}