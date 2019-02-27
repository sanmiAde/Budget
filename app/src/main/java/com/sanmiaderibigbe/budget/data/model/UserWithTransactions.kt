package com.sanmiaderibigbe.budget.data.model

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation

class UserWithTransactions {


    @Embedded
    var user: User? = null

    @Relation(parentColumn = "id",
        entityColumn = "userId",
        entity = Transaction::class)
    var currentTransactionList : List<Transaction>? = null

    fun getCurrentBalance(): Float {
        return currentTransactionList?.map { it.amount }?.sum()!!
        }
}



