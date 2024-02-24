package com.example.bubble.data.repository

import com.example.bubble.data.dao.WaterDao
import com.example.bubble.data.toWater
import com.example.bubble.data.toWaterEntity
import com.example.bubble.domain.model.Water
import com.example.bubble.domain.repository.WaterRepository

class WaterRepositoryImpl(
    private val waterDao: WaterDao
): WaterRepository {
    override fun getWaterInfo(): Water {
        return waterDao.getWaterInfo().toWater()
    }

    override suspend fun addBubbleToWater(water: Water) {
        waterDao.addBubbleToWater(water.toWaterEntity())
    }
}