package com.example.bubble.data.utils

import androidx.room.TypeConverter
import com.example.bubble.data.local.database.dbo.FocusTagEntity
import com.example.bubble.data.local.database.dbo.TagEntity
import com.example.bubble.data.local.database.dbo.WeeklyFocusEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Date

class TypeConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromWeeklyFocusEntityList(list: List<WeeklyFocusEntity>): String {
        return list.joinToString(separator = ",")
    }

    @TypeConverter
    fun toWeeklyFocusEntityList(str: String): List<WeeklyFocusEntity> {
        return str.split(",").map { WeeklyFocusEntity(it.toLong(),"") }
    }

    @TypeConverter
    fun fromTagEntityList(list: List<TagEntity>): String {
        return list.joinToString(separator = ",")
    }

    @TypeConverter
    fun toTagEntityList(str: String): List<TagEntity> {
        return str.split(",").map { TagEntity(name = it.toInt(), color = it.toInt()) }
    }

    @TypeConverter
    fun fromFocusTagEntityList(list: List<FocusTagEntity>): String {
        return list.joinToString(separator = ",")
    }

    @TypeConverter
    fun toFocusTagEntityList(str: String): List<FocusTagEntity> {
        return str.split(",").map { FocusTagEntity(tag = toTagEntityList(it)) }
    }
}