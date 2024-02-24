package com.example.bubble.domain.repository

import com.example.bubble.domain.model.Award

interface AwardRepository {
    suspend fun getAllAwards(): List<Award>

    suspend fun addAward(award: Award)
}