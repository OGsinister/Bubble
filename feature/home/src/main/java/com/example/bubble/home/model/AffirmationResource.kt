package com.example.bubble.home.model

import com.example.bubble.home.R

enum class AffirmationResource(val id: Int){
    YOU_CAN_EVERYTHING(id = R.string.you_can_everything)
}

data class Affirmation(
    val affirmationResource: AffirmationResource = AffirmationResource.YOU_CAN_EVERYTHING,
    val isVisible: Boolean = false
)