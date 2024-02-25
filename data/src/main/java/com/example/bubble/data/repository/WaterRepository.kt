package com.example.bubble.data.repository

import com.example.bubble.data.BubbleDatabase
import com.example.bubble.data.model.WaterEntity

class WaterRepository(private val database: BubbleDatabase) {
    fun getWaterInfo(): WaterEntity{
        return database.waterDao().getWaterInfo()
    }

    suspend fun addBubbleToWater(waterEntity: WaterEntity){
        return database.waterDao().addBubbleToWater(waterEntity)
    }
}