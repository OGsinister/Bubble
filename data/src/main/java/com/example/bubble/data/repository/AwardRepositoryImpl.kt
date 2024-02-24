package com.example.bubble.data.repository

import com.example.bubble.data.dao.AwardDao
import com.example.bubble.data.toAward
import com.example.bubble.data.toAwardEntity
import com.example.bubble.domain.model.Award
import com.example.bubble.domain.repository.AwardRepository

class AwardRepositoryImpl(
    private val awardDao: AwardDao
): AwardRepository {
    override suspend fun getAllAwards(): List<Award> {
        return awardDao.getAllAwards().map {
            it.toAward()
        }
    }

    override suspend fun addAward(award: Award) {
        awardDao.addAward(award.toAwardEntity())
    }
}