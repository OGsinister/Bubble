package com.example.bubble.task.useCases

import com.example.bubble.core.utils.BubbleDispatchers
import com.example.bubble.data.repository.TaskRepository
import com.example.bubble.data.utils.DatabaseResource
import com.example.bubble.data.utils.map
import com.example.bubble.data.utils.toUITask
import com.example.bubble.domain.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllTasksUseCase @Inject constructor(
    private val repository: TaskRepository,
    private val bubbleDispatchers: BubbleDispatchers
) {
    operator fun invoke(): Flow<DatabaseResource<List<Task>>> {
        return repository.getAllTasks()
            .map { databaseResource ->
                databaseResource.map { tasks ->
                    tasks.map { it.toUITask() }
                }
            }
            .flowOn(bubbleDispatchers.io)
    }
}
