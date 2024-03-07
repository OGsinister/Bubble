package com.example.bubble.data.repository

import com.example.bubble.data.BubbleDatabase
import com.example.bubble.data.local.database.dbo.TaskEntity
import com.example.bubble.data.utils.DatabaseResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val database: BubbleDatabase
) {
    fun getAllTasks(): Flow<DatabaseResource<List<TaskEntity>>> {
        return flow{
            emit(DatabaseResource.Loading())
            try {
                val cachedTasks = database.taskDao()
                    .getAllTasks()

                if(cachedTasks.isNotEmpty()){
                    emit(DatabaseResource.LoadedData(loadedData = cachedTasks))
                }else{
                    emit(DatabaseResource.Empty(message = "Empty"))
                }
            }catch (e: IOException){
                emit(DatabaseResource.Error(message = e.localizedMessage ?: "Unknown error"))
            }
        }
    }

    fun getTaskById(id: Int): DatabaseResource<TaskEntity>{
        return try {
            val cachedTask = database.taskDao()
                .getTaskById(id)

            if(cachedTask != null){
                DatabaseResource.LoadedData(loadedData = cachedTask)
            }else{
                DatabaseResource.Empty(message = "Empty")
            }
        }catch (e: IOException){
            DatabaseResource.Error(message = e.localizedMessage ?: "Unknown error")
        }
    }

    suspend fun insertTask(taskEntity: TaskEntity){
        database.taskDao().insertTask(taskEntity)
    }

    suspend fun deleteTask(taskEntity: TaskEntity){
        database.taskDao().deleteTask(taskEntity)
    }
}