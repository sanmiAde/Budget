package com.sanmiaderibigbe.budget.data.converter

import android.arch.persistence.room.TypeConverter
import com.sanmiaderibigbe.budget.data.model.TransactionType

object TransactionTypeConverter {
    @TypeConverter
    @JvmStatic
    fun fromEnumToInt(value: TransactionType?): Int? {
        return when(value) {
           TransactionType.CREDIT -> 0
           TransactionType.DEBIT-> 1
            else -> null
        }


    }
    @TypeConverter
    @JvmStatic
    fun fromIntToEnum(stateInt: Int?): TransactionType?{
        return TransactionType.values()[stateInt!!]
    }

}



