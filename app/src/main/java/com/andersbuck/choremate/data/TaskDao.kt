package com.andersbuck.choremate.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {

    @Query("SELECT * FROM task ORDER BY id")
    fun fetchTasks(): LiveData<List<Task>>

    @Query("SELECT * FROM task WHERE id = :id")
    fun fetchTask(id: String): LiveData<Task>

    @Insert
    suspend fun insert(task: Task)

    @Update
    suspend fun update(task: Task)
}