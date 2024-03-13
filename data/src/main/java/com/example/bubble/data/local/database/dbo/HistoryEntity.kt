package com.example.bubble.data.local.database.dbo

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "History")
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "is_done") val isDone: Boolean,
    @Embedded(prefix = "bubble") val bubble: BubbleEntity
)

data class BubbleEntity(
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "bubble_tag") val bubbleTag: String,
    @ColumnInfo(name = "date_time") val dateTime: String
)