package com.example.bubble.data.repository

import com.example.bubble.data.BubbleDatabase
import com.example.bubble.data.model.HistoryEntity
import com.example.bubble.data.model.WaterEntity

class HistoryRepository(private val database: BubbleDatabase) {
    suspend fun getHistory(): HistoryEntity {
        return database.historyDao().getHistory()
    }

    suspend fun addBubbleToHistory(waterEntity: WaterEntity){
        return database.historyDao().addBubbleToHistory(waterEntity)
    }
}