package com.example.bubble.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.bubble.data.local.database.dbo.BubbleEntity
import com.example.bubble.data.local.database.dbo.HistoryEntity
import com.example.bubble.data.local.database.dbo.StatisticEntity
import com.example.bubble.data.local.database.dbo.TagEntity
import com.example.bubble.data.local.database.dbo.WeeklyFocusEntity
import com.example.bubble.domain.model.WeeklyFocus
import kotlinx.coroutines.flow.Flow

@Dao
interface StatisticDao {

    @Query("SELECT COUNT(*) FROM History")
    fun getTimeCountOfSessions(): Int

    @Query("SELECT AVG(bubbledate_time) FROM History")
    fun getAverageFocusTime(): Long

    // get statistics by (today, week, month, year)
    @Query("SELECT SUM(bubbledate_time) FROM History")
    fun getWeeklyTotalFocusTime(): Long

    // это именно те данные, которые показываются в гистограмме
    @Query("SELECT bubbledate_time FROM  History")
    fun getWeeklyFocusData(): Long

    @Query("SELECT SUM(bubbledate_time) as total_focus, strftime('%w', bubbledate) as day_of_week FROM History GROUP BY day_of_week")
    fun getWeeklyFocus(): WeeklyFocusEntity

    // это именно те данные, которые показываются в круговой диаграмме тегов
    @Query("SELECT bubbletagname AS name, bubbletagcolor AS color, bubbletagicon AS icon FROM History")
    fun getTags(): TagEntity?

    // это процент успешных фокусировок ко всем фокусировкам
    @Query(" SELECT COUNT(*) FROM HISTORY WHERE is_done == 1")
    fun getSuccessfullyFocusCount(): Int

    @Query("SELECT COUNT(*) FROM HISTORY")
    fun getAllFocusCount(): Int
}