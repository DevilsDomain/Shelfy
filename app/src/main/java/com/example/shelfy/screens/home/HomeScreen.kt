package com.example.shelfy.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.shelfy.data.Book
import com.example.shelfy.data.BookItem
import com.example.shelfy.data.FakeData
import com.example.shelfy.navigation.Screens
import com.example.shelfy.screens.home.tabs.TabButtons

@Composable
fun HomeScreen(navController: NavController, selectedBook: MutableState<Book?>, selectedTabIndex: MutableState<Int>) {
    val books = FakeData.bookList


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
                items(books) { book ->
                    BookItem(book = book, navController, selectedBook)
                }
            }
        }
    }
}
