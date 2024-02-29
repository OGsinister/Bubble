package com.example.bubble.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bubble.data.local.database.dbo.AwardEntity

@Dao
interface AwardDao {
    @Query("SELECT * FROM Award")
    fun getAllAwards(): List<AwardEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAward(awardEntity: AwardEntity)
}