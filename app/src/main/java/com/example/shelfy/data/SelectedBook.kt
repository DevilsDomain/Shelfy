package com.example.shelfy.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
@Composable
fun SelectedBook(): MutableState<Book?> {
    return remember { mutableStateOf<Book?>(null) }
}