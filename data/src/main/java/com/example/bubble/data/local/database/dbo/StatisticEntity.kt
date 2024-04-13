package com.example.bubble.data.local.database.dbo

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bubble.domain.model.Tag

/*@Entity(tableName = "Statistic")
data class StatisticEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "bubble") val bubbleEntity: BubbleEntity? = null,
    @Embedded(prefix = "all_time_sessions") val allTimeSessionsEntity: AllTimeSessionsEntity? = null
)*/

@Entity(tableName = "Statistic")
data class StatisticEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "count_of_sessions") val countOfSession: Int?,
    @ColumnInfo(name = "average_focus_time") val avgFocusTime: Long?,
    @ColumnInfo(name = "weekly_total_focus_time") val weeklyFocusTime: Long?,
    @Embedded(prefix = "tag_focus_data") val tagFocusData: FocusTagEntity?,
    @ColumnInfo(name = "weekly_focus_data") val weeklyFocusMainData: Long?,
    @ColumnInfo(name = "success_percent") val successPercent: Int?
)

data class FocusTagEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @Embedded(prefix = "tag") val tag: Tag? = null,
    @ColumnInfo(name = "focus_time") val focusTime: Int? = null
)