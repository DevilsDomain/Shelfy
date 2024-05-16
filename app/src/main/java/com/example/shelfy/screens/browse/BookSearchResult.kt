package com.example.shelfy.screens.browse

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.shelfy.data.Book
import com.example.shelfy.model.Item
import com.example.shelfy.model.VolumeInfo

@Composable
fun BookSearchResult( viewModel: BrowseViewModel, item: Item) {
    Card(
        shape = MaterialTheme.shapes.medium, // Rounded corners shape
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
                        .data(item.volumeInfo?.imageLinks?.thumbnail)
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
                                text = item.volumeInfo?.title ?: "",
                                fontSize = 25.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                            item.volumeInfo?.authors?.let { authors ->
                                Text(
                                    text = authors.joinToString(", "),
                                    fontSize = 18.sp,
                                    color = Color.Black,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            Text(
                                text = item.volumeInfo?.description ?: "",
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
                        Button(
                            onClick = { viewModel.addBookToShelf(item) }
                        ) {
                            Text(text = "Add")
                        }
                    }
                }

            }
        }
    }
}

