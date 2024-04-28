package com.example.bubble.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.bubble.data.local.database.dao.AwardDao
import com.example.bubble.data.local.database.dao.HistoryDao
import com.example.bubble.data.local.database.dao.StatisticDao
import com.example.bubble.data.local.database.dao.TaskDao
import com.example.bubble.data.local.database.dbo.AwardEntity
import com.example.bubble.data.local.database.dbo.HistoryEntity
import com.example.bubble.data.local.database.dbo.StatisticEntity
import com.example.bubble.data.local.database.dbo.TaskEntity
import com.example.bubble.data.utils.TypeConverter

@Database(
    entities = [AwardEntity::class, HistoryEntity::class, TaskEntity::class, StatisticEntity::class],
    version = 1
)
@TypeConverters(TypeConverter::class)
abstract class BubbleDatabase: RoomDatabase() {
    abstract fun awardDao(): AwardDao
    abstract fun historyDao(): HistoryDao
    abstract fun taskDao(): TaskDao
    abstract fun statisticDao(): StatisticDao

    companion object{
        const val DATABASE_NAME = "Bubble"
    }
}

fun bubbleDatabase(applicationContext: Context): BubbleDatabase {
    return Room.databaseBuilder(
        context = applicationContext.applicationContext,
        klass = BubbleDatabase::class.java,
        name = BubbleDatabase.DATABASE_NAME,
    )
        .createFromAsset("database/Bubble.db")
        .build()
}