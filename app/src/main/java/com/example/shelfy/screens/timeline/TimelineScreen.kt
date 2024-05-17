package com.example.shelfy.screens.timeline

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.shelfy.db.Shelf
import com.example.shelfy.model.Item
import com.example.shelfy.screens.browse.BookSearchResult
import com.example.shelfy.screens.browse.BrowseViewModel
import com.example.shelfy.screens.browse.Searchbar
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun TimelineScreen(viewModel: TimelineViewModel) {
    val booksState = viewModel.finishedBooks.observeAsState(initial = emptyList())

    val books: List<Shelf> = booksState.value ?: emptyList()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Other UI components such as search bar can be placed here

        LazyColumn {
            items(books) { book ->
                TimelineResult(book = book)
            }
        }
    }
}


@Composable
fun TimelineResult(book: Shelf) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .padding(horizontal = 20.dp)
            .padding(8.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier.align(Alignment.Center),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                // Image
                val painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(book.imageUrl)
                        .build()
                )

                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp)
                        .aspectRatio(10f / 16f)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .padding(horizontal = 16.dp)
                        .fillMaxHeight()
                        .weight(1f)
                ) {
                    Box(
                        modifier = Modifier.weight(1f)
                    ) {
                        // Title, Authors
                        Column {
                            Text(
                                text = book.title ?: "",
                                fontSize = 25.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = book.author ?: "",
                                fontSize = 18.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = book.description ?: "",
                                fontSize = 16.sp,
                                maxLines = 5,
                                color = Color.DarkGray,
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .align(Alignment.End)
                            .padding(bottom = 20.dp)
                    ) {
                        // Display the finished date
                        book.finishedAt?.let { finishedAt ->
                            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                            val finishedDate = dateFormat.format(Date(finishedAt))
                            Text(text = "Finished: $finishedDate")
                        }
                    }
                }

            }
        }
    }
}
