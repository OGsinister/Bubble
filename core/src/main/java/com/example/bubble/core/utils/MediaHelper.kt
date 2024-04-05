package com.example.bubble.core.utils

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import com.example.bubble.core.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MediaHelper @Inject constructor(
    @ApplicationContext private val context : Context,
    private val sound: BubbleSounds = BubbleSounds.BUBBLE_POP_SOUND
)  {
    private var mediaPlayer: MediaPlayer? = null

    private fun createMediaPlayer(){
        mediaPlayer = MediaPlayer.create(context, sound.uri)
    }

    fun playSound(){
        createMediaPlayer()
        mediaPlayer?.start()
    }

    fun stopSound(){
        mediaPlayer?.release()
        mediaPlayer = null
    }
}

enum class BubbleSounds(val uri: Int) {
    BUBBLE_POP_SOUND(uri = R.raw.bubble_pop_sound)
}
