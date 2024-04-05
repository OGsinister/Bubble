package com.example.bubble.data.local.database.dbo

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "History")
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "is_done") val isDone: Boolean,
    @Embedded(prefix = "bubble") val bubble: BubbleEntity
)

data class BubbleEntity(
    @ColumnInfo(name = "id") val id: Int,
    @Embedded(prefix = "tag") val tag: TagEntity,
    @ColumnInfo(name = "date_time") val dateTime: String
)

data class TagEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: Int? = 0,
    @ColumnInfo(name = "color") val color: Int = 0,
    @ColumnInfo(name = "icon") val icon: Int = 0
)
