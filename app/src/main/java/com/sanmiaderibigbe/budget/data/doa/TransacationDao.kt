package com.sanmiaderibigbe.budget.data.doa

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.sanmiaderibigbe.budget.data.model.Transaction

@Dao
interface TransacationDao {

   @Query ("SELECT * FROM transaction_list_table ORDER BY id")
    fun getTransaction(): LiveData<List<Transaction>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(transaction: Transaction): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(books: Transaction)

    @Delete
    fun delete(books: Transaction)

 @Query("delete from transaction_list_table")
  fun deleteAll()
}
