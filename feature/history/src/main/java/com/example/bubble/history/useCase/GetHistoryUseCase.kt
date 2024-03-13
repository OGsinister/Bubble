package com.example.bubble.history.useCase

import com.example.bubble.data.repository.HistoryRepository
import com.example.bubble.data.utils.DatabaseResource
import com.example.bubble.data.utils.map
import com.example.bubble.data.utils.toUIHistory
import com.example.bubble.domain.model.History
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetHistoryUseCase @Inject constructor(
    private val repository: HistoryRepository
){
    operator fun invoke(): Flow<DatabaseResource<List<History>>> {
        return repository.getHistory()
            .map { databaseResource ->
                databaseResource.map { historyEntities ->
                    historyEntities.map { it.toUIHistory() }
                }
            }
            .flowOn(Dispatchers.IO)
    }
}