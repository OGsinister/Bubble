package com.example.bubble.domain.repository

import com.example.bubble.domain.model.History
import com.example.bubble.domain.model.Water

interface HistoryRepository {
    suspend fun getHistory(): History
    suspend fun addBubbleToHistory(water: Water)
}