package com.example.bubble.award.useCase

import com.example.bubble.award.utils.toUIAward
import com.example.bubble.core.utils.BubbleDispatchers
import com.example.bubble.data.repository.AwardRepository
import com.example.bubble.data.utils.DatabaseResource
import com.example.bubble.data.utils.map
import com.example.bubble.domain.model.Award
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllAwardsUseCase @Inject constructor(
    private val repository: AwardRepository,
    private val bubbleDispatchers: BubbleDispatchers
){
    operator fun invoke(): Flow<DatabaseResource<List<Award>>> {
        return repository.getAllAwards()
            .map { databaseResource ->
                databaseResource.map { awards ->
                    awards.map { it.toUIAward() }
                }
            }
            .flowOn(bubbleDispatchers.io)
    }
}