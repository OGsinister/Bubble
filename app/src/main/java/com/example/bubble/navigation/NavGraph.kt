package com.example.bubble.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bubble.MainViewModel
import com.example.bubble.award.presentation.AwardScreen
import com.example.bubble.history.presentation.HistoryScreen
import com.example.bubble.home.presentation.HomeScreen
import com.example.bubble.relax.presentation.RelaxScreen
import com.example.bubble.settings.presentaion.SettingsScreen
import com.example.bubble.statistics.presentation.StatisticsScreen
import com.example.bubble.water.presentation.WaterScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    innerPaddingValues: PaddingValues,
    mainViewModel: MainViewModel
){
    NavHost(
        navController = navController,
        startDestination = Screens.HomeScreen.route
    ){
        composable(route = Screens.HomeScreen.route){
            HomeScreen()
        }
        
        composable(route = Screens.AwardScreen.route){
            AwardScreen()
        }
        
        composable(route = Screens.HistoryScreen.route){
            HistoryScreen(paddingValuesTop = innerPaddingValues)
        }

        composable(route = Screens.SettingScreen.route){
            SettingsScreen(paddingValues = innerPaddingValues.calculateTopPadding())
        }

        composable(route = Screens.WaterScreen.route){
            WaterScreen()
        }

        composable(route = Screens.StatisticsScreen.route){
            StatisticsScreen(paddingValues = innerPaddingValues)
        }

        composable(route = Screens.RelaxScreen.route){
            RelaxScreen(paddingValues = innerPaddingValues)
        }
    }
}