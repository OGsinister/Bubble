package com.example.bubble.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bubble.data.dbo.BubbleEntity
import com.example.bubble.data.dbo.HistoryEntity

@Dao
interface HistoryDao {
    @Query("SELECT * FROM History")
    fun getHistory(): List<HistoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToHistory(historyEntity: HistoryEntity)
}
