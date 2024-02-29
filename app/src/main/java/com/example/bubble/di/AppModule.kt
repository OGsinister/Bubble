package com.example.bubble.di

import android.content.Context
import com.example.bubble.data.BubbleDatabase
import com.example.bubble.data.bubbleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideBubbleDatabase(@ApplicationContext context: Context): BubbleDatabase{
        return bubbleDatabase(context)
    }
}