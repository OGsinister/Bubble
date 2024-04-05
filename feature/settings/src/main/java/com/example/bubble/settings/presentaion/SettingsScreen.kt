package com.example.bubble.settings.presentaion

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bubble.core.ui.theme.BubbleTheme
import com.example.bubble.core.ui.utils.BubbleImage
import com.example.bubble.core.ui.utils.GradientColumn
import com.example.bubble.core.utils.getDominantColor
import com.example.bubble.domain.model.Settings
import com.example.bubble.domain.model.UserSettings
import com.example.bubble.settings.R
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

@Composable
fun LoadedScreen(
    modifier: Modifier = Modifier,
    settings: Settings,
    paddingValues: Dp,
    viewModel: SettingsViewModel
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = paddingValues)
    ) {
        settings.userSettings?.let {
            UserSection(userSettings = it)
        }

        SettingItem(
            settings = settings,
            viewModel = viewModel
        )
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
            ){
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
            ){
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
            ){
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
            ){
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
    userSettings: UserSettings
) {
    val userAvatar = userSettings.avatar
        .takeIf { it != 0 } ?: com.example.bubble.core.R.drawable.default_user_avatar
    val dominantColor = getDominantColor(LocalContext.current, userAvatar)?.rgb

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        BubbleTheme.colors.backgroundGradientColorDark1,
                        Color(dominantColor!!),
                        BubbleTheme.colors.backgroundGradientColorDark1.copy(0.1f),
                    )
                )
            )
            .padding(BubbleTheme.shapes.basePadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BubbleImage(
            size = 100.dp,
            image = userAvatar,
            onClick = {
                // open user's gallery
            }
        )

        userSettings.name?.let {
            Text(
                text = it,
                style = BubbleTheme.typography.heading,
                color = BubbleTheme.colors.primaryTextColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}