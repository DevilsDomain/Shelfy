package com.example.shelfy.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter.Companion.tint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.shelfy.R
import com.example.shelfy.db.Shelf
import com.example.shelfy.screens.browse.BrowseViewModel
import com.example.shelfy.screens.browse.NotesField
import com.example.shelfy.ui.theme.LibbyGreen
import java.sql.Date
import java.text.SimpleDateFormat

@Composable
fun DetailsScreen(selectedBook: MutableState<Shelf?>, viewModel: DetailsViewModel) {
    val book = selectedBook.value
    val statuses = listOf("New", "Reading", "Finished")
    val initialStatus = remember { mutableStateMapOf<String, Boolean>().apply {
        statuses.forEach { status ->
            this[status] = book?.status == status
        }
    } }
    val notes = remember { mutableStateOf(book?.notes ?: "") }

    var selectedRating by remember { mutableStateOf(book?.rating ?: 0) } // Initialize with book's rating

    fun onStatusRadioButtonClicked(status: String) {
        book?.let {
            viewModel.updateBookStatus(it.id, status)
        }
        initialStatus.forEach { (key, _) ->
            initialStatus[key] = key == status
        }
    }

    fun onStarClicked(index: Int) {
        selectedRating = index + 1
        viewModel.updateBookRating(book?.id ?: "", selectedRating)
    }

    fun onNotesChanged(newNotes: String) {
        notes.value = newNotes
        book?.let { viewModel.updateBookNotes(it.id, newNotes) }
    }

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
                        val painter = rememberAsyncImagePainter(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(book!!.imageUrl)
                                .build()
                        )
                        Image(
                            painter = painter,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    statuses.forEach { status ->
                        RadioButton(
                            selected = initialStatus[status] ?: false,
                            onClick = { onStatusRadioButtonClicked(status) },
                            colors = RadioButtonDefaults.colors(selectedColor = LibbyGreen)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = status)
                    }
                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Title Text
                    Text(
                        text = book!!.title ?: "",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                    )

                    // Author Text
                    Text(
                        text = book!!.author ?: "",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )

                    // Synopsis Text
                    Text(
                        text = book!!.description ?: "",
                        fontSize = 16.sp,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        val maxRating = 5 // Maximum rating value

                        repeat(maxRating) { index ->
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "Star",
                                tint = if (index < selectedRating) LibbyGreen else Color.LightGray,
                                modifier = Modifier
                                    .padding(horizontal = 5.dp, vertical = 20.dp)
                                    .clickable { onStarClicked(index) }
                                    .size(40.dp)
                            )
                        }
                    }

                    NotesField(notes = notes.value, onNotesChanged = ::onNotesChanged)

                    // Display finishedAt date if book is marked as finished
                    if (book.status == "Finished" && book.finishedAt != null) {
                        Text(
                            text = "Finished: ${SimpleDateFormat("dd/MM/yyyy").format(Date(book.finishedAt!!))}",
                            fontSize = 16.sp,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }

            } else {
                Text(text = "Something went wrong ;(", fontSize = 24.sp)
            }
        }
    }
}



