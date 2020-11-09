package com.andersbuck.choremate.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey val id: String,
    val chore: Chore,
    val user: User
) {}