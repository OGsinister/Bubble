package com.example.bubble.task.useCases

import com.example.bubble.data.repository.TaskRepository
import com.example.bubble.data.utils.toTaskEntity
import com.example.bubble.domain.model.Task
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(task: Task){
        repository.deleteTask(
            task.toTaskEntity()
        )
    }
}