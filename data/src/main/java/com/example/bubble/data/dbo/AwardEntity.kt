package com.example.bubble.data.dbo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Award")
data class AwardEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int? = null,
    @ColumnInfo(name = "name") val name: String? = null,
    @ColumnInfo(name = "title") val title: String? = null,
    @ColumnInfo(name = "icon") val icon: Int? = null,
    @ColumnInfo(name = "is_open") val isOpen: Boolean? = null
)

