package com.example.bubble.data.repository

import android.util.Log
import com.example.bubble.data.BubbleDatabase
import com.example.bubble.data.local.database.dbo.HistoryEntity
import com.example.bubble.data.utils.DatabaseResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class HistoryRepository @Inject constructor(
    private val database: BubbleDatabase
) {
    fun getHistory(): Flow<DatabaseResource<List<HistoryEntity>>> {
        return flow {

            emit(DatabaseResource.Default())
            try{
                val cachedHistory = database.historyDao().getHistory()
                Log.d("checkData",cachedHistory.toString())
                if(cachedHistory.isEmpty()){
                    emit(DatabaseResource.Empty(message = "Empty list"))
                }else{
                    emit(DatabaseResource.LoadedData(loadedData = cachedHistory))
                }
            }catch (e: IOException){
                emit(DatabaseResource.Error(message = e.localizedMessage!!))
            }catch (e: Exception){
                emit(DatabaseResource.Error(message = e.localizedMessage!!))
            }
        }
    }

    suspend fun addBubbleToHistory(historyEntity: HistoryEntity){
        return database.historyDao().addToHistory(historyEntity)
    }
}