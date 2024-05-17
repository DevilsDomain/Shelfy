package com.example.shelfy.data

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.shelfy.db.Shelf
import com.example.shelfy.navigation.Screens

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BookItem(
    book: Shelf,
    navController: NavController,
    selectedBook: MutableState<Shelf?>,
    onDeleteBook: () -> Unit // Add onDeleteBook lambda parameter
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .padding(8.dp)
            .combinedClickable(
                onClick = {
                    selectedBook.value = book
                    navController.navigate(Screens.Details.screen)
                },
                onLongClick = { onDeleteBook() } // Call onDeleteBook on long press
            )
    ) {
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(book.imageUrl)
                .build()
        )
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(10f / 16f),
            contentScale = ContentScale.Crop
        )
    }
}