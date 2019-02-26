package com.sanmiaderibigbe.budget.data.doa

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.sanmiaderibigbe.budget.data.model.User

@Dao
interface UserDoa {
    @Query("SELECT * FROM users_table ORDER BY id")
    fun getTransaction(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(transaction: User): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(books: User)

    @Delete
    fun delete(books: User)
}