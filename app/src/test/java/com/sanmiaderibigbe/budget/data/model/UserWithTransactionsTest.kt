package com.sanmiaderibigbe.budget.data.model

import org.junit.Test

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import java.util.*

class UserWithTransactionsTest {

    val sanmiWithAllHisTransation = UserWithTransactions()

    @Before
    fun setup() {
        val transaction1 =  Transaction(
            0,
            1000f,
            Date(),
            TransactionType.DEBIT,
            0
        )
        val transaction2 = Transaction(
            0,
            1000f,
            Date(),
            TransactionType.CREDIT,
            0
        )
        val transaction3 =  Transaction(
            0,
            1000f,
            Date(),
            TransactionType.DEBIT,
            0
        )
        val transaction4  =  Transaction(
            0,
            1000f,
            Date(),
            TransactionType.CREDIT,
            0
        )
        val transaction5 = Transaction(
            0,
            10000f,
            Date(),
            TransactionType.DEBIT,
            0
        )

        val sanmi = User(0, "sanmi", 10000f)

        val sanmiCurrentTransactionList = listOf(transaction1,transaction2, transaction3, transaction4, transaction5)
        sanmiWithAllHisTransation.user = sanmi
        sanmiWithAllHisTransation.currentTransactionList = sanmiCurrentTransactionList
    }


    @Test
    fun getCurrentBalance_allTransactionsDoneByUSer_ShouldReturnSum() {
        val currentBalance : Float = sanmiWithAllHisTransation.getCurrentBalance()
        assertThat(currentBalance, `is`(20000f))
    }
}