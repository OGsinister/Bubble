package com.example.bubble.presentation

import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.bubble.MainViewModel
import com.example.bubble.core.ui.theme.BubbleTheme
import com.example.bubble.home.R
import com.example.bubble.navigation.NavGraph
import com.example.bubble.navigation.NavItem
import com.example.bubble.navigation.Screens
import com.example.bubble.presentation.utils.DrawerHeader
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BubbleModalNavDrawer(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel,
    navController: NavHostController
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val navItems = listOf(
        NavItem(route = Screens.HomeScreen.route, stringResource(id = R.string.home), painterResource(id = R.drawable.ic_home_icon)),
        NavItem(route = Screens.WaterScreen.route, stringResource(id = R.string.water), painterResource(id = R.drawable.ic_water_icon)),
        NavItem(route = Screens.AwardScreen.route, stringResource(id = R.string.awards), painterResource(id = R.drawable.ic_awards_icon)),
        NavItem(route = Screens.HistoryScreen.route, stringResource(id = R.string.history), painterResource(id = R.drawable.ic_history_icon)),
        NavItem(route = Screens.StatisticsScreen.route, stringResource(id = R.string.statistic), painterResource(id = R.drawable.ic_statistic_icon)),
        NavItem(route = Screens.SettingScreen.route, stringResource(id = R.string.settings), painterResource(id = R.drawable.ic_settings_icon)),
        NavItem(route = Screens.RelaxScreen.route, stringResource(id = R.string.relax), painterResource(id = R.drawable.ic_relax_icon))
    )
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = BubbleTheme.colors.backgroundGradientColorDark1
            ) {
                DrawerHeader(viewModel = mainViewModel, navController = navController)
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
                            selectedItemIndex = index
                            scope.launch {
                                drawerState.close()
                            }
                            navController.navigate(navigationItem.route)
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
            content = { paddingValues ->
                NavGraph(
                    navController = navController,
                    innerPaddingValues = paddingValues,
                    mainViewModel = mainViewModel
                )
            }
        )
    }
}