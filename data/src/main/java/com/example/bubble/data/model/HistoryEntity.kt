package com.example.bubble.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "History")
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "is_done") val isDone: Boolean,
    @Embedded(prefix = "bubble.") val bubble: BubbleEntity
)
data class BubbleEntity(
    @ColumnInfo(name = "volume") val volume: Float,
    @Embedded(prefix = "tag.") val tag: TagEntity,
    @ColumnInfo(name = "date_time") val dateTime: String
)
data class TagEntity(
    @Embedded(prefix = "name.") val name: String
)
enum class TagNames(val color: String){
    STUDY(color = "Red"),
    WORK(color = "Yellow"),
    READ(color = "Orange"),
    HOME(color = "Purple"),
    SELF_DEVELOP(color = "Blue"),
}