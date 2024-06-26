package com.example.bubble.di

import android.content.Context
import com.example.bubble.core.utils.BubbleDispatchers
import com.example.bubble.core.utils.BubbleSounds
import com.example.bubble.core.utils.MediaHelperBuilder
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

    @Provides
    @Singleton
    fun provideDispatchers() = BubbleDispatchers()

    @Provides
    fun provideMediaHelper(@ApplicationContext context: Context): MediaHelperBuilder {
        return MediaHelperBuilder(context)
    }
}