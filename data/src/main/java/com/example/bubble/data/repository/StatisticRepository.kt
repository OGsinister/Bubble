package com.example.bubble.data.repository

import android.util.Log
import com.example.bubble.data.BubbleDatabase
import com.example.bubble.data.local.database.dbo.BubbleEntity
import com.example.bubble.data.local.database.dbo.FocusTagEntity
import com.example.bubble.data.local.database.dbo.HistoryEntity
import com.example.bubble.data.local.database.dbo.StatisticEntity
import com.example.bubble.data.local.database.dbo.TagEntity
import com.example.bubble.data.local.database.dbo.WeeklyFocusEntity
import com.example.bubble.data.utils.DatabaseResource
import com.example.bubble.data.utils.toTag
import com.example.bubble.domain.model.History
import com.example.bubble.domain.model.WeeklyFocus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import java.io.IOException
import javax.inject.Inject

class StatisticRepository @Inject constructor(
    private val database: BubbleDatabase
) {
    fun getAllStatistic(): Flow<DatabaseResource<List<StatisticEntity>>> {
        return flow {

            emit(DatabaseResource.Loading())
            try {
                getCountOfSessions()
                getAverageFocusTime()
                getWeeklyTotalFocusTime()
                getTagFocusTime()
                getSuccessfullyFocusPercent()
                getAllFocusCount()
                getWeeklyFocus()

                val statisticCached = database.statisticDao().getStatistics()

                /*val cachedStatistic = StatisticEntity(
                    countOfSession = getCountOfSessions(),
                    avgFocusTime = getAverageFocusTime(),
                    weeklyFocusTime = getWeeklyTotalFocusTime(),
                    tagFocusData = FocusTagEntity(
                        tag = getTagFocusTime()?.toTag(),
                        focusTime = getWeeklyTotalFocusTime().toInt()
                    ),
                    weeklyFocusMainData = database.statisticDao().getWeeklyFocus(),
                    successPercent = (getSuccessfullyFocusPercent() / getAllFocusCount()).toFloat()
                )*/

                /*if (cachedStatistic.tagFocusData != null){
                    emit(DatabaseResource.LoadedData(loadedData = cachedStatistic))
                }*/

                if (statisticCached.isEmpty()){
                    emit(DatabaseResource.Empty(message = "empty"))
                }
                Log.d("checkMf", statisticCached.toString())
                emit(DatabaseResource.LoadedData(loadedData = statisticCached, message = null))
            } catch (e: IOException) {
                emit(DatabaseResource.Error(message = e.localizedMessage.toString()))
            }
        }
    }

    private fun getCountOfSessions(): Int = database.statisticDao().getTimeCountOfSessions()
    private fun getAverageFocusTime(): Long = database.statisticDao().getAverageFocusTime()
    private fun getWeeklyTotalFocusTime(): Long = database.statisticDao().getWeeklyTotalFocusTime()
    private fun getTagFocusTime(): TagEntity? = database.statisticDao().getTags()
    private fun getSuccessfullyFocusPercent(): Int = database.statisticDao().getSuccessfullyFocusCount()
    private fun getAllFocusCount(): Int = database.statisticDao().getAllFocusCount()
    private fun getWeeklyFocus(): WeeklyFocusEntity = database.statisticDao().getWeeklyFocus()
}