package com.example.bubble.core.use_cases

import android.content.Context
import android.media.MediaPlayer
import android.provider.MediaStore.Audio.Media
import com.example.bubble.core.utils.BubbleSounds
import com.example.bubble.core.utils.MediaHelperBuilder
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import javax.inject.Inject

class MediaPlayerIWorkUseCase @Inject constructor(
    private val mediaHelperBuilder: MediaHelperBuilder,
    @ApplicationContext val context: Context
) {
    private var alarm: MediaPlayer? = null

    suspend fun executeBubblePop(){
        MediaHelperBuilder
            .setBubbleSound(BubbleSounds.BUBBLE_POP_SOUND)
            .createMediaPlayer(context)
            .play()
            .build()
        delay(100)
        MediaHelperBuilder
            .stop()
    }

    suspend fun executeAlarm() {
        alarm = MediaHelperBuilder
            .setBubbleSound(BubbleSounds.BUBBLE_ALARM_SOUND)
            .createMediaPlayer(context)
            .play()
            .setLooping()
            .build()
    }

    suspend fun stopAlarm() {
        alarm?.stop()
    }
}