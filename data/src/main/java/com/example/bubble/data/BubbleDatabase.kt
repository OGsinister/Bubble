package com.example.bubble.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bubble.data.local.database.dao.AwardDao
import com.example.bubble.data.local.database.dao.HistoryDao
import com.example.bubble.data.local.database.dbo.AwardEntity
import com.example.bubble.data.local.database.dbo.HistoryEntity

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
        context = applicationContext.applicationContext,
        klass = BubbleDatabase::class.java,
        name = BubbleDatabase.DATABASE_NAME
    ).build()
}