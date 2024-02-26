package com.example.bubble.data.repository

import com.example.bubble.data.BubbleDatabase
import com.example.bubble.data.dbo.AwardEntity
import com.example.bubble.domain.utils.DatabaseResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class AwardRepository(private val database: BubbleDatabase) {
    fun getAllAwards(): Flow<DatabaseResource<List<AwardEntity>>>{
        return flow {

            emit(DatabaseResource.Default())
            try {
                emit(DatabaseResource.Loading())
                val cachedAwards = database.awardDao()
                    .getAllAwards()
                if(cachedAwards.isEmpty()){
                    emit(DatabaseResource.Empty(data = emptyList()))
                }
                emit(DatabaseResource.Success(data = cachedAwards))
            }catch (e: IOException){
                emit(DatabaseResource.Error(message = e.localizedMessage))
            }catch (e: Exception){
                emit(DatabaseResource.Error(message = e.localizedMessage))
            }
        }
    }

    suspend fun addAward(awardEntity: AwardEntity){
        return database.awardDao().addAward(awardEntity)
    }
}