package com.example.shelfy.screens.home.tabs.tab_new

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.shelfy.data.BookItem
import com.example.shelfy.db.Shelf
import com.example.shelfy.screens.home.tabs.TabButtons

@Composable
fun TabNewScreen(navController: NavController, selectedTabIndex: MutableState<Int>, viewModel: TabNewViewModel, selectedBook: MutableState<Shelf?>) {
    val books by viewModel.newBooks.observeAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TabButtons(navController = navController, selectedTabIndex = selectedTabIndex)
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(books ?: emptyList()) { book ->
                    BookItem(book = book, navController, selectedBook)
                }
            }
        }
    }
}