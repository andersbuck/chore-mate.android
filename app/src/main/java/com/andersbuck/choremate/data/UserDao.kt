package com.andersbuck.choremate.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Query("SELECT * FROM user ORDER BY username")
    fun fetchUsers(): LiveData<List<User>>

    @Query("SELECT * FROM user WHERE id = :id")
    fun fetchUser(id: String): LiveData<User>

    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)
}