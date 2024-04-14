package com.example.bubble.data.local.database.dbo

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.bubble.domain.model.Tag

@Entity(tableName = "Statistic")
data class StatisticEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "count_of_sessions") val countOfSession: Int?,
    @ColumnInfo(name = "average_focus_time") val avgFocusTime: Long?,
    @ColumnInfo(name = "weekly_total_focus_time") val weeklyFocusTime: Long?,
    @ColumnInfo(name = "success_percent") val successPercent: Float?,
    @Embedded(prefix = "tag_focus_data") val tagFocusData: FocusTagEntity?,

    @Embedded(prefix = "weekly_focus_data") val weeklyFocusMainData: WeeklyFocusEntity,
    @ColumnInfo(name = "day_of_week") val dayOfWeek: String? = null
)

data class FocusTagEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @Embedded(prefix = "tag") val tag: Tag? = null,
    @ColumnInfo(name = "focus_time") val focusTime: Int? = null
)

data class WeeklyFocusEntity(
    @ColumnInfo(name = "total_focus") val totalTime: Long? = 0,
    @ColumnInfo(name = "day_of_week") val dayOfWeek: String? = ""
)

