package com.example.bubble.statistics.useCases

import com.example.bubble.core.utils.BubbleDispatchers
import com.example.bubble.data.repository.StatisticRepository
import com.example.bubble.data.utils.DatabaseResource
import com.example.bubble.data.utils.map
import com.example.bubble.data.utils.toStatistic
import com.example.bubble.domain.model.Statistic
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetStatisticDataUseCase @Inject constructor(
    private val repository: StatisticRepository,
    private val bubbleDispatchers: BubbleDispatchers
) {
    operator fun invoke(): Flow<DatabaseResource<Statistic>> {
        return repository.getAllStatistic()
            .map { resource ->
                resource.map {
                    it.toStatistic()
                }
            }
            .flowOn(bubbleDispatchers.io)
    }
}