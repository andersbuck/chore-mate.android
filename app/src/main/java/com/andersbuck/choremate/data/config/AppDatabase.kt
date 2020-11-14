package com.andersbuck.choremate.data.config

import androidx.room.Database
import androidx.room.RoomDatabase
import com.andersbuck.choremate.data.User
import com.andersbuck.choremate.data.UserDao

@Database(entities = arrayOf(User::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}