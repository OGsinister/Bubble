package com.example.bubble.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.bubble.data.dao.AwardDao
import com.example.bubble.data.dao.HistoryDao
import com.example.bubble.data.dbo.AwardEntity
import com.example.bubble.data.dbo.HistoryEntity

@Database(entities = [AwardEntity::class, HistoryEntity::class], version = 1)
abstract class BubbleDatabase: RoomDatabase() {
    abstract fun awardDao(): AwardDao
    abstract fun historyDao(): HistoryDao

    companion object{
        const val DATABASE_NAME = "Bubble"
    }
}

fun bubbleDatabase(applicationContext: Context): BubbleDatabase {
    return Room.databaseBuilder(
        context = checkNotNull(applicationContext.applicationContext),
        klass = BubbleDatabase::class.java,
        name = BubbleDatabase.DATABASE_NAME
    ).build()
}