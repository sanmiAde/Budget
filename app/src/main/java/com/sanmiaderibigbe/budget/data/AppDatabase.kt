package com.sanmiaderibigbe.budget.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.sanmiaderibigbe.budget.data.converter.DateConverter
import com.sanmiaderibigbe.budget.data.converter.TransactionTypeConverter
import com.sanmiaderibigbe.budget.data.doa.TransacationDao
import com.sanmiaderibigbe.budget.data.model.Transaction


@Database(entities = [Transaction::class], version = 1)
@TypeConverters(TransactionTypeConverter::class, DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun transactionDao() : TransacationDao


    companion object {
        private var sBuilder: RoomDatabase.Builder<AppDatabase>? = null
        private const val DB_NAME = "database"


        /***
         * Creates database
         * @memoryOnly: create an in memory database sBuilder if true.
         */
        @Synchronized
        fun getDatabase(context: Context, memoryOnly: Boolean): AppDatabase {
            when (sBuilder) {
                null -> sBuilder = when {
                    memoryOnly -> Room.inMemoryDatabaseBuilder(context.applicationContext, AppDatabase::class.java)
                    else -> Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME)
                }
            }
            return sBuilder!!.build()


        }
    }
}
