package com.andersbuck.choremate.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(val username: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}