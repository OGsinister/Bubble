package com.example.bubble.award.utils

typealias coreDrawable = com.example.bubble.core.R.drawable

fun getImageByAwardId(id: Int): Int {
    return when(id) {
        1 -> coreDrawable.ic_award_first_time
        2 -> coreDrawable.ic_award_professor
        3 -> coreDrawable.ic_award_selfie
        else -> coreDrawable.ic_award_master
    }
}