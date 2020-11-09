package com.andersbuck.choremate.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ChoreDao {

    @Query("SELECT * FROM chore ORDER BY name")
    fun fetchChores(): LiveData<List<Chore>>

    @Query("SELECT * FROM chore WHERE id = :id")
    fun fetchChore(id: String): LiveData<Chore>

    @Insert
    suspend fun insert(chore: Chore)

    @Update
    suspend fun update(chore: Chore)
}