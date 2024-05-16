package com.example.shelfy.screens.browse

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shelfy.data.FakeData

@Composable
fun BrowseScreen(viewModel: BrowseViewModel) {
    val books by viewModel.books.observeAsState(emptyList())

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Searchbar { query ->
            viewModel.searchBooks(query)
        }
        Spacer(modifier = Modifier.height(5.dp))
        LazyColumn {
            items(books) { book ->
                BookSearchResult(viewModel= viewModel, item = book)
            }
        }
    }
}