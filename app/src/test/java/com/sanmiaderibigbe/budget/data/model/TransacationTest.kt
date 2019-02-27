package com.sanmiaderibigbe.budget.data.model


import com.sanmiaderibigbe.budget.data.model.Transaction
import com.sanmiaderibigbe.budget.data.model.TransactionType
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat


import org.junit.Test

import java.util.*

class TransacationTest{
    @Test
    fun amount_shouldBeNegative(){
        val transaction = Transaction(
            0,
            1000f,
            Date(),
            TransactionType.CREDIT,
            0
        )
       assertThat(transaction.amount, `is`(-1000f))
    }

    @Test
    fun amount_shouldBePositive(){
        val transaction = Transaction(
            0,
            1000f,
            Date(),
            TransactionType.DEBIT,
            0
        )
        assertThat(
            "should be positive",
            transaction.amount,
            `is`<Float>(1000f)
        )
    }

}