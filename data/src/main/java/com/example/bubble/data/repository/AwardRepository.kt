package com.example.bubble.data.repository

import com.example.bubble.data.BubbleDatabase
import com.example.bubble.data.model.AwardEntity

class AwardRepository(private val database: BubbleDatabase) {
    suspend fun getAllAwards(): List<AwardEntity> {
        return database.awardDao().getAllAwards()
    }

    suspend fun addAward(awardEntity: AwardEntity){
        return database.awardDao().addAward(awardEntity)
    }
}