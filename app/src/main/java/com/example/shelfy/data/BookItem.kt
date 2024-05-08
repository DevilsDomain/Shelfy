package com.example.shelfy.data

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.shelfy.navigation.Screens

@Composable
fun BookItem(book: Book, navController: NavController) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = book.imageResId),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(10f / 16f)
                .clickable {
                    navController.navigate(Screens.Details.screen)
                },
            contentScale = ContentScale.Crop
        )
    }
}