package com.example.shelfy.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.shelfy.db.Shelf

@Composable
fun SelectedBook(): MutableState<Shelf?> {
    return remember { mutableStateOf<Shelf?>(null) }
}