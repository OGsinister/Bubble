package com.example.bubble.data.repository

import com.example.bubble.data.BubbleDatabase
import com.example.bubble.data.local.database.dbo.AwardEntity
import com.example.bubble.data.local.sharedPref.AwardSharedPref
import com.example.bubble.data.utils.DatabaseResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class AwardRepository @Inject constructor(
    private val database: BubbleDatabase,
    private val awardSharedPref: AwardSharedPref
) {
    fun getAllAwards(): Flow<DatabaseResource<List<AwardEntity>>>{
        return flow {
            emit(DatabaseResource.Loading())
            try {
                val cachedAwards = database.awardDao()
                    .getAllAwards()

                if(cachedAwards.isNotEmpty()){
                    emit(DatabaseResource.LoadedData(loadedData = cachedAwards))
                    awardSharedPref.updateAward(cachedAwards)
                }else{
                    emit(DatabaseResource.Empty(message = "Empty list"))
                }

            }catch (e: IOException){
                emit(DatabaseResource.Error(message = e.localizedMessage))
            }catch (e: Exception){
                emit(DatabaseResource.Error(message = e.localizedMessage))
            }
        }
    }

    suspend fun updateAward(awardEntity: AwardEntity){
        awardEntity.id?.let {
            return database.awardDao().updateAward(it)
        }
    }

    suspend fun addAward(awardEntity: AwardEntity){
        database.awardDao().addAward(awardEntity)
    }

    suspend fun getUserUnlockedAwards(): List<AwardEntity> {
        return database.awardDao().getUserUnlockedAwards()
    }
}