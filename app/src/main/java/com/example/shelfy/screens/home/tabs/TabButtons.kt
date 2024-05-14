package com.example.shelfy.screens.home.tabs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.shelfy.navigation.Screens
import com.example.shelfy.ui.theme.LibbyGreen

@Composable
fun TabButtons(navController: NavController, selectedTabIndex: MutableState<Int>) {
    val buttonColors = listOf(
        if (selectedTabIndex.value == 0) LibbyGreen else Color.Gray,
        if (selectedTabIndex.value == 1) LibbyGreen else Color.Gray,
        if (selectedTabIndex.value == 2) LibbyGreen else Color.Gray
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = {
                navController.navigate(Screens.New.screen) {
                    popUpTo(0)
                }
                selectedTabIndex.value = 0
            },
            colors = ButtonDefaults.buttonColors(containerColor = buttonColors[0])
        ) {
            Text(text = "New")
        }
        Spacer(modifier = Modifier.width(16.dp))
        Button(
            onClick = {
                navController.navigate(Screens.Reading.screen) {
                    popUpTo(0)
                }
                selectedTabIndex.value = 1
            },
            colors = ButtonDefaults.buttonColors(containerColor = buttonColors[1])
        ) {
            Text(text = "Reading")
        }
        Spacer(modifier = Modifier.width(16.dp))
        Button(
            onClick = {
                navController.navigate(Screens.Finished.screen) {
                    popUpTo(0)
                }
                selectedTabIndex.value = 2
            },
            colors = ButtonDefaults.buttonColors(containerColor = buttonColors[2])
        ) {
            Text(text = "Finished")
        }
    }
}