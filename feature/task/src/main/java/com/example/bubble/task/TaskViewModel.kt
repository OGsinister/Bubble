package com.example.bubble.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bubble.core.utils.BubbleDispatchers
import com.example.bubble.data.utils.DatabaseResource
import com.example.bubble.domain.model.Task
import com.example.bubble.task.model.TaskState
import com.example.bubble.task.model.TasksState
import com.example.bubble.task.useCases.DeleteTaskUseCase
import com.example.bubble.task.useCases.GetAllTasksUseCase
import com.example.bubble.task.useCases.GetTaskByIdUseCase
import com.example.bubble.task.useCases.InsertTaskUseCase
import com.example.bubble.task.utils.toTasksState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val getAllTasksUseCase: GetAllTasksUseCase,
    private val getTaskByIdUseCase: GetTaskByIdUseCase,
    private val insertTaskUseCase: InsertTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val bubbleDispatchers: BubbleDispatchers
): ViewModel() {

    private val _task = MutableStateFlow<TaskState>(TaskState.DefaultState)
    internal var task = _task.asStateFlow()

    internal val tasksState: StateFlow<TasksState> = getAllTasksUseCase()
        .map {
            it.toTasksState()
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, TasksState.DefaultState)

    suspend fun getTaskById(id: Int) {
        viewModelScope.launch(bubbleDispatchers.io) {
            _task.value = getTaskByIdUseCase(id).toTasksState()
        }
    }

    suspend fun insertTask(task: Task){
        viewModelScope.launch(bubbleDispatchers.io) {
            insertTaskUseCase(task)
        }
    }

    suspend fun deleteTask(task: Task){
        viewModelScope.launch(bubbleDispatchers.io) {
            deleteTaskUseCase(task)
        }
    }
}