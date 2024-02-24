package com.example.bubble.data.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bubble.data.model.HistoryEntity
import com.example.bubble.data.model.WaterEntity

interface HistoryDao {
    @Query("SELECT * FROM History")
    fun getHistory(): HistoryEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBubbleToHistory(waterEntity: WaterEntity)
}