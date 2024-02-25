package com.example.bubble.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.bubble.data.dao.AwardDao
import com.example.bubble.data.dao.HistoryDao
import com.example.bubble.data.dao.WaterDao
import com.example.bubble.data.model.AwardEntity
import com.example.bubble.data.model.HistoryEntity
import com.example.bubble.data.model.WaterEntity

@Database(entities = [AwardEntity::class, HistoryEntity::class, WaterEntity::class], version = 1)
@TypeConverters(Converter::class)
abstract class BubbleDatabase: RoomDatabase() {
    abstract fun awardDao(): AwardDao
    abstract fun historyDao(): HistoryDao
    abstract fun waterDao(): WaterDao

    companion object{
        const val DATABASE_NAME = "Bubble"
    }
}

fun bubbleDatabase(applicationContext: Context): BubbleDatabase {
    return Room.databaseBuilder(
        checkNotNull(applicationContext.applicationContext),
        BubbleDatabase::class.java,
        BubbleDatabase.DATABASE_NAME
    ).build()
}