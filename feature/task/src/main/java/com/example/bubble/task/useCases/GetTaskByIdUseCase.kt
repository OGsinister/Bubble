package com.example.bubble.task.useCases

import com.example.bubble.data.repository.TaskRepository
import com.example.bubble.data.utils.DatabaseResource
import com.example.bubble.data.utils.map
import com.example.bubble.data.utils.toUITask
import com.example.bubble.domain.model.Task
import javax.inject.Inject

class GetTaskByIdUseCase @Inject constructor(
    private val repository: TaskRepository
) {
    operator fun invoke(id: Int): DatabaseResource<Task>{
        return repository.getTaskById(id = id)
            .map {
                it.toUITask()
            }
    }
}