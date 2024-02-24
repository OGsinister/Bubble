package com.example.bubble.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Water")
data class WaterEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = 1,
    val commonWater: Int,
    val title: String,
    val comparison: String
)