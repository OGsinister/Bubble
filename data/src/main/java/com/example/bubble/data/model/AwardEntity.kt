package com.example.bubble.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Award")
data class AwardEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val title: String,
    val icon: Int,
    val isOpen: Boolean
)