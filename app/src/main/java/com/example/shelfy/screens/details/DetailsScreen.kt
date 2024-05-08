package com.example.shelfy.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shelfy.data.Book
import com.example.shelfy.data.SelectedBook

@Composable
fun DetailsScreen(selectedBook: MutableState<Book?>) {
    val book = selectedBook.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (book != null) {
            Text(text = "Title: ${book.title}", fontSize = 24.sp)
            // Display other book details here
        } else {
            Text(text = "No book selected", fontSize = 24.sp)
        }
    }
}
