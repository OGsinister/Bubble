package com.example.bubble.data

import androidx.room.TypeConverter
import com.example.bubble.data.model.TagNames

class Converter {
    @TypeConverter
    fun toTagNames(value: String) = enumValueOf<TagNames>(value)

    @TypeConverter
    fun fromTagNames(value: TagNames) = value.name
}