package com.example.bubble.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bubble.domain.model.Bubble

@Entity(tableName = "History")
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val isDone: Boolean,
    val bubble: Bubble
)