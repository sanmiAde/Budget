package com.sanmiaderibigbe.budget.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "transaction_list_table")

/***
 * Transaction  is the representation of each transaction done by the user
 * @id   The id of each transaction
 * @amount The amount of each transaction
 * @Date the day transaction was done
 * @transactionType The type of transaction done
 */
class Transaction(
    @PrimaryKey(autoGenerate = true) var id: Long, transactionAmount : Float,
    var date: Date,
    var transactionType: TransactionType,
    var amount: Float = when (transactionType) {
        TransactionType.CREDIT -> Math.abs(transactionAmount) * -1
        else -> transactionAmount
    }
){
    constructor() : this(0, 0f, Date(), TransactionType.DEBIT)
}



enum class TransactionType (val transactionType: Int){
    DEBIT(0),
    CREDIT(1),
}
