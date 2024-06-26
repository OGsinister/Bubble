package com.example.bubble.settings.presentaion

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bubble.core.R
import com.example.bubble.core.ui.theme.BubbleTheme
import com.example.bubble.core.ui.utils.BubbleImage
import com.example.bubble.core.ui.utils.BubbleTextField
import com.example.bubble.core.ui.utils.ChangeUserNameDialog
import com.example.bubble.core.ui.utils.GradientColumn
import com.example.bubble.core.utils.Constants
import com.example.bubble.core.utils.getDominantColor
import com.example.bubble.domain.model.Settings
import com.example.bubble.settings.SettingsViewModel
import com.example.bubble.settings.model.SettingsEvent
import com.example.bubble.settings.model.SettingsState
import com.example.bubble.settings.utils.SettingCard

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = hiltViewModel(),
    paddingValues: Dp
) {
    val state by viewModel.state.collectAsState()
    val currentState = state

    GradientColumn(
        padding = BubbleTheme.shapes.noPadding,
        accentGradientColor = BubbleTheme.colors.backgroundGradientSettingsAccentColor3,
        content = {
            when (currentState) {
                SettingsState.DefaultState -> DefaultScreen()
                SettingsState.IsLoadingState -> IsLoadingScreen()
                is SettingsState.ErrorState -> ErrorScreen(errorMessage = currentState.message)
                is SettingsState.LoadedState -> {
                    LoadedScreen(
                        settings = currentState.settings,
                        paddingValues = paddingValues,
                        viewModel = viewModel
                    )
                }
            }
        }
    )
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun LoadedScreen(
    modifier: Modifier = Modifier,
    settings: Settings,
    paddingValues: Dp,
    viewModel: SettingsViewModel
) {
    var userNameValue by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = paddingValues)
    ) {
        UserSection(viewModel = viewModel)

        SettingItem(
            settings = settings,
            viewModel = viewModel
        )

        AnimatedVisibility(viewModel.isOpenUserNameDialog.value) {
            Column(
                modifier = Modifier
                    .padding(BubbleTheme.shapes.basePadding)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = stringResource(id = R.string.type_your_name),
                    style = BubbleTheme.typography.heading,
                    color = BubbleTheme.colors.primaryTextColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ){
                    BubbleTextField(
                        value = userNameValue,
                        onValueChange = { userNameValue = it },
                        placeholderText = viewModel.user.value.name,
                        focusedTextColor = BubbleTheme.colors.chartLineColor
                    )

                    Icon(
                        imageVector = Icons.Default.Done,
                        contentDescription = null,
                        tint = BubbleTheme.colors.bubbleButtonColor,
                        modifier = Modifier.clickable {
                            viewModel.changeDialog(!viewModel.isOpenUserNameDialog.value)
                            viewModel.event(SettingsEvent.ChangeUserName(name = userNameValue))
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun SettingItem(
    modifier: Modifier = Modifier,
    settings: Settings,
    viewModel: SettingsViewModel
) {
    val checkedAffirmation = rememberSaveable {
        mutableStateOf(settings.isAffirmationOn)
    }

    val checkedPopBubble = rememberSaveable {
        mutableStateOf(settings.isUserPopBubble)
    }

    val checkedSound = rememberSaveable {
        mutableStateOf(settings.isSoundOn)
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(BubbleTheme.shapes.largePadding),
        colors = CardDefaults.cardColors(
            Color.White.copy(alpha = 0.05f)
        ),
        shape = BubbleTheme.shapes.cornerStyle
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BubbleTheme.shapes.itemsPadding),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Функциональные",
                    style = BubbleTheme.typography.heading,
                    color = BubbleTheme.colors.primaryTextColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SettingCard(
                    checked = checkedAffirmation.value,
                    onCheckChange = {
                        checkedAffirmation.value = !checkedAffirmation.value
                        viewModel.event(SettingsEvent.ChangeAffirmationSetting(checkedAffirmation.value))
                    },
                    text = "Включить аффермации"
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SettingCard(
                    checked = checkedPopBubble.value,
                    onCheckChange = {
                        checkedPopBubble.value = !checkedPopBubble.value
                        viewModel.event(SettingsEvent.ChangeBubblePopSetting(checkedPopBubble.value))
                    },
                    text = "Лопнуть Bubble самому"
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SettingCard(
                    checked = checkedSound.value,
                    onCheckChange = {
                        checkedSound.value = !checkedSound.value
                        viewModel.event(SettingsEvent.ChangeSoundSetting(checkedSound.value))
                    },
                    text = "Звук"
                )
            }
        }
    }
}

@Composable
fun UserSection(
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel
) {
    val context = LocalContext.current
    val user by viewModel.user.collectAsState()
    val photoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) {
        it?.let {
            viewModel.event(
                SettingsEvent.ChangeUserAvatar(
                    avatar = BitmapFactory.decodeStream(
                        context.contentResolver.openInputStream(it)
                    )
                )
            )
        }
    }
    val dominantColor = getDominantColor(
        LocalContext.current,
        user.image
    )?.rgb

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        BubbleTheme.colors.backgroundGradientColorDark1,
                        Color(dominantColor!!),
                        BubbleTheme.colors.backgroundGradientColorDark1.copy(0.4f),
                        BubbleTheme.colors.backgroundGradientColorDark1.copy(0.38f),
                        BubbleTheme.colors.backgroundGradientColorDark1
                    )
                )
            )
            .padding(BubbleTheme.shapes.basePadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BubbleImage(
            size = 100.dp,
            image = user.image,
            onClick = {
                photoPicker.launch(
                    PickVisualMediaRequest(
                        ActivityResultContracts.PickVisualMedia.ImageOnly
                    )
                )
                viewModel.updateThirdAward()
            }
        )

        Text(
            text = user.name,
            style = BubbleTheme.typography.heading,
            color = BubbleTheme.colors.primaryTextColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .clickable {
                    viewModel.changeDialog(!viewModel.isOpenUserNameDialog.value)
                }
        )
    }
}