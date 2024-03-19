package com.example.bubble.home.presentation

import android.annotation.SuppressLint
import androidx.compose.animation.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bubble.core.ui.theme.BubbleTheme
import com.example.bubble.home.HomeViewModel
import com.example.bubble.home.R
import com.example.bubble.home.model.HomeState
import com.example.bubble.home.model.NavItem
import com.example.bubble.home.utils.DrawerHeader
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val bubbleTimer by viewModel.bubbleTimer.collectAsState()
    val tag by viewModel.tag.collectAsState()

    val repeatableColorOne = remember { Animatable(Color.Magenta) }
    val repeatableColorTwo = remember { Animatable(Color(0xFF673AB7)) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }
    val scope = rememberCoroutineScope()
    val listColors = remember {
        listOf(
            Color(0xFF3F51B5),
            Color(0xFF673AB7),
            Color(0xFF2196F3)
        )
    }
    val navItems = listOf(
        NavItem(stringResource(id = R.string.home), painterResource(id = R.drawable.ic_home_icon)),
        NavItem(stringResource(id = R.string.water), painterResource(id = R.drawable.ic_water_icon)),
        NavItem(stringResource(id = R.string.awards), painterResource(id = R.drawable.ic_awards_icon)),
        NavItem(stringResource(id = R.string.history), painterResource(id = R.drawable.ic_history_icon)),
        NavItem(stringResource(id = R.string.statistic), painterResource(id = R.drawable.ic_statistic_icon)),
        NavItem(stringResource(id = R.string.settings), painterResource(id = R.drawable.ic_settings_icon)),
        NavItem(stringResource(id = R.string.relax), painterResource(id = R.drawable.ic_relax_icon))
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = BubbleTheme.colors.backgroundGradientColorDark1
            ) {
                DrawerHeader()
                Spacer(Modifier.height(5.dp))
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(Modifier.height(12.dp))
                navItems.forEachIndexed { index, navigationItem ->
                    NavigationDrawerItem(
                        modifier = Modifier
                            .padding(BubbleTheme.shapes.basePadding),
                        icon = {
                            Icon(
                                modifier = Modifier
                                    .size(24.dp),
                                painter = navigationItem.icon,
                                contentDescription = "Nav item icon"
                            )
                        },
                        label = {
                            Text(
                                text = navigationItem.title,
                                style = BubbleTheme.typography.body,
                                color = BubbleTheme.colors.primaryTextColor
                            )
                        },
                        selected = index == selectedItemIndex,
                        onClick = {
                            // navigate to some screen
                            selectedItemIndex = index
                            scope.launch {
                                drawerState.close()
                            }
                        },
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = BubbleTheme.colors.selectedContainerColor,
                            unselectedContainerColor = Color.Transparent,
                            selectedIconColor = BubbleTheme.colors.notificationColor
                        )
                    )
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.app_name),
                            color = BubbleTheme.colors.primaryTextColor
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu icon",
                                tint = Color.White
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(Color.Transparent)
                )
            },
            content = {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .background(
                            Brush.linearGradient(
                                colors = listOf(
                                    BubbleTheme.colors.backgroundGradientColorDark1,
                                    BubbleTheme.colors.backgroundGradientColorDark2,
                                    BubbleTheme.colors.backgroundGradientHomeAccentColor1
                                )
                            )
                        ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    when (state) {
                        HomeState.DefaultState -> {
                            DefaultHomeScreen(
                                viewModel = viewModel,
                                bubbleTimer = bubbleTimer,
                                tag = tag
                            )
                        }

                        HomeState.FocusRunning -> {
                            FocusHomeScreen(
                                viewModel = viewModel,
                                repeatableColorOne = repeatableColorOne,
                                repeatableColorTwo = repeatableColorTwo,
                                listColors = listColors
                            )
                        }
                    }
                }
            }
        )
    }
}