package com.example.bubble.core.use_cases

import com.example.bubble.core.utils.MediaHelper
import kotlinx.coroutines.delay
import javax.inject.Inject

class MediaPlayerIWorkUseCase @Inject constructor(
    private val mediaHelper: MediaHelper
) {
    suspend operator fun invoke(){
        mediaHelper.playSound()
        delay(500)
        mediaHelper.stopSound()
    }
}