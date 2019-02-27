package com.sanmiaderibigbe.budget.data.converter

import android.arch.persistence.room.TypeConverter
import com.sanmiaderibigbe.budget.data.model.TransactionType

object TransactionTypeConverter {
    @TypeConverter
    @JvmStatic
    fun fromEnumToInt(value: TransactionType?): Int? {
        return when(value) {
           TransactionType.CREDIT -> 1
           TransactionType.DEBIT-> 0
            else -> null
        }


    }
    @TypeConverter
    @JvmStatic
    fun fromIntToEnum(stateInt: Int?): TransactionType?{
        return TransactionType.values()[stateInt!!]
    }

}



