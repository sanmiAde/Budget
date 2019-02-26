package com.sanmiaderibigbe.budget.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "users_table")
class User(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0,
    val name: String = "",
    val currentAmount : Float = 0f
)