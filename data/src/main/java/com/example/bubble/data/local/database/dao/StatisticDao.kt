package com.example.bubble.data.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.bubble.data.local.database.dbo.TagEntity

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

   /* @Query("SELECT SUM(bubbledate_time) as total_sum FROM History")
    fun getWeeklyFocusData(): Long*/


    // это именно те данные, которые показываются в круговой диаграмме тегов
    /*@Query("SELECT bubbletagcolor, " +
            "bubbletagicon, " +
            "bubbletagname, " +
            "bubbledate_time FROM History, Statistic WHERE tag_focus_dataid == id")*/
    @Query("SELECT bubbletagname AS name, bubbletagcolor AS color, bubbletagicon AS icon FROM History")
    fun getTags(): TagEntity?

    // это процент успешных фокусировок ко всем фокусировкам
    @Query(" SELECT COUNT(*) FROM HISTORY WHERE is_done == 1")
    fun getSuccessfullyFocusCount(): Int

    @Query("SELECT COUNT(*) FROM HISTORY")
    fun getAllFocusCount(): Int
}