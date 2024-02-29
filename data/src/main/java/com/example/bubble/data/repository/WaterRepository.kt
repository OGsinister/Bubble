package com.example.bubble.data.repository

interface WaterRepository {
    fun updateWater(newValue: Float)

    fun getWater(): Float?
}