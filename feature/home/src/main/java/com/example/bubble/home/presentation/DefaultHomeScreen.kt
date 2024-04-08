package com.example.bubble.home.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.bubble.core.ui.theme.BubbleTheme
import com.example.bubble.core.ui.utils.BubbleDialog
import com.example.bubble.core.ui.utils.ChangeUserNameDialog
import com.example.bubble.core.ui.utils.TagUI
import com.example.bubble.core.utils.toTimeUIFormat
import com.example.bubble.home.HomeViewModel
import com.example.bubble.home.R
import com.example.bubble.home.model.BubbleTimer
import com.example.bubble.home.model.HomeEvents
import com.example.bubble.home.utils.BubbleButton
import com.example.bubble.home.utils.TagBottomSheet
import com.example.bubble.home.utils.TimeBottomSheet

@Composable
fun DefaultHomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    bubbleTimer: BubbleTimer,
    tag: TagUI
) {
    var showTimeBottomSheet by viewModel.showTimeBottomSheet
    var showTagBottomSheet by viewModel.showTagBottomSheet
    val dialogState = viewModel.dialogState
    val showDialog = viewModel.showDialog

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .size(256.dp),
                painter = painterResource(id = R.drawable.ic_default_screen_image),
                contentDescription = "Default screen image"
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                modifier = Modifier
                    .clickable {
                        showTimeBottomSheet = true
                    },
                text = bubbleTimer.millisInFuture.toTimeUIFormat(),
                color = Color.White,
                style = BubbleTheme.typography.heading
            )

            Box(
                modifier = Modifier
                    .size(200.dp),
                contentAlignment = Alignment.Center
            ) {
                BubbleButton(
                    onClick = {
                        viewModel.event(HomeEvents.RunFocus)
                    },
                    text = stringResource(id = R.string.start)
                )
            }

            Row(
                modifier = Modifier
                    .height(40.dp)
                    .width(65.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(BubbleTheme.colors.tagBackgroundColor)
                    .padding(BubbleTheme.shapes.basePadding)
                    .clickable {
                        showTagBottomSheet = true
                    },
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .drawBehind {
                            drawCircle(
                                color = tag.color
                            )
                        }
                )
                Text(
                    text = stringResource(id = R.string.tag),
                    style = BubbleTheme.typography.smallText,
                    color = BubbleTheme.colors.primaryTextColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }

    if (showTimeBottomSheet) {
        TimeBottomSheet(
            viewModel = viewModel,
            bubbleTimer = bubbleTimer
        )
    }

    if (showTagBottomSheet) {
        TagBottomSheet(viewModel = viewModel)
    }

    AnimatedVisibility(
        visible = showDialog.value,
        enter = scaleIn(),
        exit = scaleOut()
    ) {
        when (dialogState.value) {
            true -> {
                BubbleDialog(
                    onDismiss = {
                        showDialog.value = false
                    },
                    color = BubbleTheme.colors.bubbleButtonColor,
                    tittle = stringResource(id = R.string.dialog_text_success)
                )
            }

            false -> {
                BubbleDialog(
                    onDismiss = {
                        showDialog.value = false
                    },
                    color = BubbleTheme.colors.errorColor,
                    tittle = stringResource(id = R.string.dialog_text_fail)
                )
            }
        }
    }
}