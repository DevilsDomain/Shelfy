package com.example.shelfy.screens.home.tabs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.shelfy.navigation.Screens

@Composable
fun TabFinishedScreen(navController: NavController, selectedTabIndex: MutableState<Int>) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        TabButtons(navController, selectedTabIndex)
        Text(text = "Finished screen")
    }
}
