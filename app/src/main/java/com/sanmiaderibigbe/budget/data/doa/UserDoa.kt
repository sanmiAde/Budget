package com.sanmiaderibigbe.budget.data.doa

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.sanmiaderibigbe.budget.data.model.User
import com.sanmiaderibigbe.budget.data.model.UserWithTransactions

@Dao
interface UserDoa {
    @Query("SELECT * FROM users_table ORDER BY id")
    fun getUser(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(transaction: User): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(books: User)

    @Delete
    fun delete(books: User)


    @Query("SELECT * FROM users_table ")
    @Transaction
    fun getAllBooksWithNotes() :List<UserWithTransactions>

    @Query("delete from users_table")
    fun deleteAll()
}