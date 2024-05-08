package com.example.shelfy.screens.browse

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shelfy.data.FakeData

@Composable
fun BrowseScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Searchbar()
        Spacer(modifier = Modifier.height(5.dp))
        val books = FakeData.bookList

        LazyColumn {
            items(books) { book ->
                BookSearchResult(book = book)
            }
        }
    }
}