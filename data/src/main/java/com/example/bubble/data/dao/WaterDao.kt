package com.example.bubble.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bubble.data.model.WaterEntity

@Dao
interface WaterDao {
    @Query("SELECT * FROM Water")
    fun getWaterInfo(): WaterEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBubbleToWater(waterEntity: WaterEntity)
}