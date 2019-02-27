package com.sanmiaderibigbe.budget.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "users_table")
class User(
    @PrimaryKey(autoGenerate = true)
    var id : Long = 0,
    var name: String = "",
    var currentAmount : Float = 0f

)