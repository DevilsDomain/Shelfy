package com.example.shelfy.data

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.shelfy.R
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
    val context = LocalContext.current

    LaunchedEffect(book.imageUrl) {
        val imageLoader = coil.ImageLoader(context)

        val request = ImageRequest.Builder(context)
            .data(book.imageUrl)
            .allowHardware(false) // Disable hardware bitmaps, useful for large images
            .listener(
                onStart = {
                    Log.d("BookItem", "Loading image URL: ${book.imageUrl}")
                },
                onSuccess = { _, _ ->
                    Log.d("BookItem", "Image loaded successfully: ${book.imageUrl}")
                },
                onError = { _, result ->
                    Log.e("BookItem", "Error loading image: ${result.throwable}")
                    Toast.makeText(context, "Error loading image", Toast.LENGTH_SHORT).show()
                }
            )
            .build()

        val result = (imageLoader.execute(request) as? SuccessResult)?.drawable
    }

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
//                .placeholder(R.drawable.placeholder) // Placeholder image
//                .error(R.drawable.error_image) // Error image
                .crossfade(true) // Enable crossfade animation
                .build()
        )

        Image(
            painter = painter,
            contentDescription = book.title,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(10f / 16f),
            contentScale = ContentScale.Crop
        )
    }
}
