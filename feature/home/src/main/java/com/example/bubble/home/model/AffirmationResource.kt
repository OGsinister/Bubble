package com.example.bubble.home.model

import com.example.bubble.home.R

enum class AffirmationResource(val id: Int){
    YOU_CAN_EVERYTHING(id = R.string.you_can_everything),
    GOOD_FOR_YOU(id = R.string.good_for_you),
    YOU_ARE_THE_BEST(id = R.string.the_best),
    IM_SO_PROUD_OF_YOU(id = R.string.proud)
}

data class Affirmation(
    val affirmationResource: AffirmationResource? = AffirmationResource.YOU_CAN_EVERYTHING,
    val isVisible: Boolean = false
)