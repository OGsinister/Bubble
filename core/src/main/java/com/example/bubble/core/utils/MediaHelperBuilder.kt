package com.example.bubble.core.utils

import android.content.Context
import android.media.MediaPlayer
import com.example.bubble.core.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MediaHelperBuilder @Inject constructor(
    @ApplicationContext private val context : Context
)  {
    companion object Builder {
        private var mediaPlayer: MediaPlayer? = null
        private var bubbleSound: BubbleSounds = BubbleSounds.BUBBLE_POP_SOUND

        fun createMediaPlayer(context: Context) = apply {
            mediaPlayer = MediaPlayer.create(context, bubbleSound.uri)
        }

        fun setBubbleSound(sound: BubbleSounds) = apply {
            bubbleSound = sound
        }

        fun play() = apply {
            mediaPlayer?.start()
        }

        fun stop() = apply {
            mediaPlayer?.stop()
        }

        fun setLooping() = apply {
            mediaPlayer?.isLooping = true
        }

        fun build(): MediaPlayer? {
            return mediaPlayer
        }
    }
}

enum class BubbleSounds(val uri: Int) {
    BUBBLE_POP_SOUND(uri = R.raw.bubble_pop_sound),
    BUBBLE_ALARM_SOUND(uri = R.raw.bubble_alarm)
}
