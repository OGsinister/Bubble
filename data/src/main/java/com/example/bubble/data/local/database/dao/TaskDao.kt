package com.example.bubble.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bubble.data.local.database.dbo.TaskEntity

@Dao
interface TaskDao {
    @Query("SELECT * FROM Task")
    fun getAllTasks(): List<TaskEntity>

    @Query("SELECT * FROM Task WHERE id = :id")
    fun getTaskById(id: Int): TaskEntity?

    // needs to return Notification
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(taskEntity: TaskEntity)

    @Delete
    suspend fun deleteTask(taskEntity: TaskEntity)
}