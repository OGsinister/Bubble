package com.example.bubble.core.ui.utils

import androidx.compose.ui.graphics.Color
import com.example.bubble.core.R

data class TagUI(
    val id: Int = 0,
    val name: Int? = TagNames.WORK.tagName,
    val color: Color = TagColors.WORK_COLOR.color,
    val icon: Int = TagIcons.WORK_ICON.icon
)

enum class TagColors(val color: Color){
    WORK_COLOR(color = Color(0xFFFA8949 )),
    STUDY_COLOR(color = Color(0xFF65CA87)),
    READING_COLOR(color = Color(0xFFE73333)),
    SPORT_COLOR(color = Color(0xFF3258DD)),
    HOME_COLOR(color = Color(0xFFA141EC))
}

enum class TagNames(val tagName: Int){
    WORK(tagName = R.string.tag_work),
    STUDY(tagName = R.string.tag_study),
    READING(tagName = R.string.tag_reading),
    SPORT(tagName = R.string.tag_sport),
    HOME(tagName = R.string.tag_home)
}

enum class TagIcons(val icon: Int){
    WORK_ICON(icon = R.drawable.ic_tag_work_icon),
    STUDY_ICON(icon = R.drawable.ic_tag_study_icon),
    READING_ICON(icon = R.drawable.ic_tag_reading_icon),
    SPORT_ICON(icon = R.drawable.ic_tag_sport_icon),
    HOME_ICON(icon = R.drawable.ic_tag_home_icon)
}