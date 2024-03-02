package com.example.bubble.water.useCases

import com.example.bubble.data.local.sharedPref.WaterSharedPref
import javax.inject.Inject

class AddWaterUseCase @Inject constructor(
    private val sharedPref: WaterSharedPref
) {
    operator fun invoke(newBubble: Int){
        sharedPref.updateBubbleCount(newBubble)
    }
}