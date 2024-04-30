package com.example.bubble.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bubble.data.local.database.dbo.AwardEntity
import com.example.bubble.data.local.database.dbo.TagEntity

@Dao
interface AwardDao {
    @Query("SELECT * FROM Award")
    fun getAllAwards(): List<AwardEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAward(awardEntity: AwardEntity)

    @Query("UPDATE Award SET is_open = 1 WHERE id = :id")
    suspend fun updateAward(id: Int)

    @Query("SELECT * FROM Award WHERE is_open = 1")
    suspend fun getUserUnlockedAwards(): List<AwardEntity>

    @Query("SELECT bubbletagname as name, bubbletagcolor as color, bubbletagicon as icon, SUM(bubbledate_time) as total_time FROM HISTORY WHERE is_done = 1")
    fun getDoneTags(): List<TagEntity>
}