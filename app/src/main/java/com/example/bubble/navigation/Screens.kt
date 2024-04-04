package com.example.bubble.navigation

sealed class Screens(val route: String) {
    data object HomeScreen: Screens(route = "Home")
    data object AwardScreen: Screens(route = "Award")
    data object HistoryScreen: Screens(route = "History")
    data object SettingScreen: Screens(route = "Settings")
    data object StatisticsScreen: Screens(route = "Statistics")
    data object WaterScreen: Screens(route = "Water")
    data object RelaxScreen: Screens(route = "Relax")
}