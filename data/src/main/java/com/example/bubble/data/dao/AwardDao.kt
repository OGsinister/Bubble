package com.example.bubble.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bubble.data.model.AwardEntity

@Dao
interface AwardDao {
    @Query("SELECT * FROM Award")
    suspend fun getAllAwards(): List<AwardEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAward(awardEntity: AwardEntity)
}