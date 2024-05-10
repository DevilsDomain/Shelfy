package com.example.shelfy.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shelfy.R
import com.example.shelfy.data.Book
import com.example.shelfy.screens.browse.NotesField

@Composable
fun DetailsScreen(selectedBook: MutableState<Book?>) {
    val book = selectedBook.value

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            if (book != null) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(500.dp)
                        .padding(top = 20.dp, bottom = 20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Card(
                        shape = MaterialTheme.shapes.medium,
                        modifier = Modifier
                            .padding(8.dp)
                            .aspectRatio(10f / 16f)
                            .fillMaxSize()
                    ) {
                        Image(
                            painter = painterResource(id = book.imageResId),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "New")
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Finished")
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Reading")
                    }
                }
                Column (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Title Text
                    Text(
                        text = book.title,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                    )

                    // Author Text
                    Text(
                        text = book.author,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )

                    // Synopsis Text
                    Text(
                        text = book.synopsis,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        repeat(5) {
                            Icon(
                                painter = painterResource(id = R.drawable.star_empty),
                                contentDescription = "Empty Star",
                                modifier = Modifier
                                    .padding(horizontal = 5.dp)
                            )
                        }
                    }
                    NotesField()
                }

            } else {
                Text(text = "Something went wrong ;(", fontSize = 24.sp)
            }
        }
    }
}




