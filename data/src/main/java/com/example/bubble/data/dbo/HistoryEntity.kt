package com.example.bubble.data.dbo

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "History")
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "is_done") val isDone: Boolean,
    @Embedded val bubble: BubbleEntity
)

data class BubbleEntity(
    @ColumnInfo(name = "volume") val volume: Float,
    @ColumnInfo(name = "date_time") val dateTime: String
)