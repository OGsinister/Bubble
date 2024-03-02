package com.example.bubble.award.useCase

import com.example.bubble.award.utils.AwardCodes
import com.example.bubble.data.local.sharedPref.AwardSharedPref
import com.example.bubble.data.repository.AwardRepository
import javax.inject.Inject

class UpdateAwardUseCase @Inject constructor(
    private val repository: AwardRepository,
    private val awardSharedPref: AwardSharedPref
) {
    suspend fun updateAward(code: AwardCodes) {
        when (code) {
            AwardCodes.FIRST_BUBBLE_CLICK -> {
                val awardFromJson = awardSharedPref.getAward()
                val awardEntity = awardFromJson.find {
                    it.id == code.id
                }
                if (awardEntity != null) {
                    repository.updateAward(awardEntity)
                }
            }

            AwardCodes.FIRST_BUBBLE_CANCELLED -> TODO("Not implemented")
            AwardCodes.FIRST_BUBBLE_DONE -> {
                val awardFromJson = awardSharedPref.getAward()
                val awardEntity = awardFromJson.find {
                    it.id == code.id
                }
                if (awardEntity != null) {
                    repository.updateAward(awardEntity)
                }
            }
        }
    }
}