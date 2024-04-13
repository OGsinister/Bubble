package com.example.bubble.data.repository

import com.example.bubble.data.BubbleDatabase
import com.example.bubble.data.local.database.dbo.FocusTagEntity
import com.example.bubble.data.local.database.dbo.StatisticEntity
import com.example.bubble.data.local.database.dbo.TagEntity
import com.example.bubble.data.utils.DatabaseResource
import com.example.bubble.data.utils.toTag
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class StatisticRepository @Inject constructor(
    private val database: BubbleDatabase
) {
    fun getAllStatistic(): Flow<DatabaseResource<StatisticEntity>> {
        return flow {

            emit(DatabaseResource.Loading())
            try {
                val cachedStatistic = StatisticEntity(
                    countOfSession = getCountOfSessions(),
                    avgFocusTime = getAverageFocusTime(),
                    weeklyFocusTime = getWeeklyTotalFocusTime(),
                    tagFocusData = FocusTagEntity(
                        tag = getTagFocusTime()?.toTag(),
                        focusTime = getWeeklyTotalFocusTime().toInt()
                    ),
                    weeklyFocusMainData = getWeeklyFocusData(),
                    successPercent = getSuccessfullyFocusPercent()
                )

                if (cachedStatistic.tagFocusData != null){
                    emit(DatabaseResource.LoadedData(loadedData = cachedStatistic))
                }
                else{
                    emit(DatabaseResource.Empty(message = "Empty"))
                }

            } catch (e: IOException) {
                emit(DatabaseResource.Error(message = e.localizedMessage.toString()))
            }
        }
    }

    private fun getCountOfSessions(): Int = database.statisticDao().getTimeCountOfSessions()
    private fun getAverageFocusTime(): Long = database.statisticDao().getAverageFocusTime()
    private fun getWeeklyTotalFocusTime(): Long = database.statisticDao().getWeeklyTotalFocusTime()
    private fun getWeeklyFocusData(): Long = database.statisticDao().getWeeklyFocusData()
    private fun getTagFocusTime(): TagEntity? = database.statisticDao().getTags()
    private fun getSuccessfullyFocusPercent(): Int = database.statisticDao().getSuccessfullyFocusCount()
    private fun getAllFocusCount(): Int = database.statisticDao().getAllFocusCount()
}