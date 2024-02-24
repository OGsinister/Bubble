package com.example.bubble.data.repository

import com.example.bubble.data.dao.HistoryDao
import com.example.bubble.data.toHistory
import com.example.bubble.data.toWaterEntity
import com.example.bubble.domain.model.History
import com.example.bubble.domain.model.Water
import com.example.bubble.domain.repository.HistoryRepository

class HistoryRepositoryImpl(
    private val historyDao: HistoryDao
): HistoryRepository{
    override suspend fun getHistory(): History {
        return historyDao.getHistory().toHistory()
    }

    override suspend fun addBubbleToHistory(water: Water) {
        historyDao.addBubbleToHistory(water.toWaterEntity())
    }
}