package com.example.bubble.domain.repository

import com.example.bubble.domain.model.Water

interface WaterRepository {
    fun getWaterInfo(): Water
    suspend fun addBubbleToWater(water: Water)
}