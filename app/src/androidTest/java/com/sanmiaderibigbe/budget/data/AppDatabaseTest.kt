package com.sanmiaderibigbe.budget.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.support.test.runner.AndroidJUnit4
import org.junit.runner.RunWith
import android.support.test.InstrumentationRegistry
import com.sanmiaderibigbe.budget.data.doa.TransacationDao
import org.junit.Before
import org.junit.Test
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
    }

    @Test
    fun insert_newTransaction_shouldBeInserted(){
        val currentListOfTransacation = doa.getTransaction().blockingObserve()
        //assertThat(currentListOfTransacation, hasSize<List<Transacation>>(0))
    }


    private fun <T> LiveData<T>.blockingObserve(): T? {
        var value: T? = null
        val latch = CountDownLatch(1)

        val observer = Observer<T> { t ->
            value = t
            latch.countDown()
        }

        observeForever(observer)

        latch.await(2, TimeUnit.SECONDS)
        return value
    }

}