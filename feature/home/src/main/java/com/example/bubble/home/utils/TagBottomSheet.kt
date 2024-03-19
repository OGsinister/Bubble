package com.example.bubble.home.utils

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.bubble.core.ui.theme.BubbleTheme
import com.example.bubble.core.ui.utils.BubbleBottomSheet
import com.example.bubble.core.ui.utils.TagColors
import com.example.bubble.core.ui.utils.TagIcons
import com.example.bubble.core.ui.utils.TagNames
import com.example.bubble.core.ui.utils.TagUI
import com.example.bubble.home.HomeViewModel
import com.example.bubble.home.R
import com.example.bubble.home.model.HomeEvents
import kotlinx.coroutines.launch

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TagBottomSheet(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel
) {
    val showTimerSheet = viewModel.showTagBottomSheet
    val sheetScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()
    val selectedIndex = rememberSaveable {
        mutableIntStateOf(viewModel.tag.value.currentTag)
    }
    val tags = listOf(
        TagUI(name = TagNames.WORK.tagName, color = TagColors.WORK_COLOR.color, icon = TagIcons.WORK_ICON.icon),
        TagUI(name = TagNames.STUDY.tagName, color = TagColors.STUDY_COLOR.color, icon = TagIcons.STUDY_ICON.icon),
        TagUI(name = TagNames.READING.tagName, color = TagColors.READING_COLOR.color, icon = TagIcons.READING_ICON.icon),
        TagUI(name = TagNames.SPORT.tagName, color = TagColors.SPORT_COLOR.color, icon = TagIcons.SPORT_ICON.icon),
        TagUI(name = TagNames.HOME.tagName, color = TagColors.HOME_COLOR.color, icon = TagIcons.HOME_ICON.icon),
    )

    BubbleBottomSheet(
        onDismiss = {
            showTimerSheet.value = false
        },
        sheetState = sheetState,
        content = {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = stringResource(id = R.string.select_tag),
                    style = BubbleTheme.typography.heading,
                    color = BubbleTheme.colors.notificationColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ){
                    TextButton(
                        onClick = {
                            sheetScope.launch {
                                sheetState.hide()
                            }.invokeOnCompletion {
                                if(!sheetState.isVisible){
                                    showTimerSheet.value = false
                                }
                            }
                        }
                    ) {
                        Text(
                            text = stringResource(id = R.string.save)
                        )
                    }
                }

                LazyVerticalGrid(columns = GridCells.Fixed(3)) {
                    itemsIndexed(tags) { index, tag ->
                        Column(
                            modifier = Modifier
                                .height(80.dp)
                                .width(120.dp)
                                .padding(BubbleTheme.shapes.basePadding)
                                .clickable {
                                    selectedIndex.intValue = index
                                    viewModel.updateCurrentTag(
                                        tag.copy(currentTag = selectedIndex.intValue)
                                    )
                                    viewModel.event(HomeEvents.SelectTag(tag.toTag()))
                                }
                                .alpha(if (selectedIndex.intValue == index) 1f else 0.1f),
                            verticalArrangement = Arrangement.SpaceBetween,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .drawBehind {
                                        drawCircle(
                                            color = tag.color,
                                            radius = size.maxDimension / 2f
                                        )
                                    },
                                contentAlignment = Alignment.Center
                            ){
                                Icon(
                                    painter = painterResource(id = tag.icon),
                                    contentDescription = "Icon tag",
                                    modifier = Modifier.size(24.dp),
                                    tint = Color.White
                                )
                            }
                            Text(
                                text = stringResource(id = tag.name!!),
                                style = BubbleTheme.typography.body,
                                color = BubbleTheme.colors.primaryTextColor,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }
        }
    )
}