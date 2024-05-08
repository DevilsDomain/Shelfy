package com.example.shelfy.navigation

sealed class Screens(val screen: String) {
    data object Home: Screens("home")
    data object Browse: Screens("browse")
    data object Timeline: Screens("timeline")
    data object Details: Screens("details")

}