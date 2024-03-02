package com.example.bubble.award.useCase

import com.example.bubble.data.local.database.dbo.AwardEntity
import com.example.bubble.data.repository.AwardRepository
import javax.inject.Inject

class AddAwardUseCase @Inject constructor(
    private val repository: AwardRepository
) {
    suspend operator fun invoke(awardEntity: AwardEntity){
        repository.addAward(awardEntity)
    }
}