package com.andersbuck.choremate.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Chore(
    @PrimaryKey val id: String,
    val name: String,
    val effort: Int
) {}