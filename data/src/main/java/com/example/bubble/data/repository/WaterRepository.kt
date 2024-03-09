package com.example.bubble.data.repository

interface WaterRepository {
    fun updateBubbleCount(newBubble: Int)
    fun getBubbleCount(): Int?
}