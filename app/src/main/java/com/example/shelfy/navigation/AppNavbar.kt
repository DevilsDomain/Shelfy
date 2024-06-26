package com.example.shelfy.navigation

import android.app.Application
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shelfy.data.SelectedBook
import com.example.shelfy.db.AppDatabase
import com.example.shelfy.db.ShelfRepository
import com.example.shelfy.screens.browse.BrowseScreen
import com.example.shelfy.screens.browse.BrowseViewModel
import com.example.shelfy.screens.details.DetailsScreen
import com.example.shelfy.screens.details.DetailsViewModel
import com.example.shelfy.screens.home.HomeScreen
import com.example.shelfy.screens.home.HomeViewModel
import com.example.shelfy.screens.home.tabs.tab_new.TabFinishedScreen
import com.example.shelfy.screens.home.tabs.tab_new.TabFinishedViewModel
import com.example.shelfy.screens.home.tabs.tab_new.TabNewScreen
import com.example.shelfy.screens.home.tabs.tab_new.TabNewViewModel
import com.example.shelfy.screens.home.tabs.tab_new.TabReadingScreen
import com.example.shelfy.screens.home.tabs.tab_new.TabReadingViewModel
import com.example.shelfy.screens.timeline.TimelineScreen
import com.example.shelfy.screens.timeline.TimelineViewModel
import com.example.shelfy.ui.theme.LibbyGreen



@Composable
fun AppNavbar () {
    val navController = rememberNavController()
    val selected = remember {
        mutableStateOf(Icons.Default.Home)
    }
    val selectedBook = SelectedBook()
    val selectedTabIndex = remember { mutableStateOf(-1) }
    val applicationContext = (LocalContext.current.applicationContext as Application)
    val database = AppDatabase.getDatabase(applicationContext)
    val repository = ShelfRepository(database.shelfDao())




    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = LibbyGreen
            ) {
                IconButton(onClick = {
                    selectedTabIndex.value = -1
                    selected.value = Icons.Default.Home
                    navController.navigate(Screens.Home.screen){
                        popUpTo(0)
                    }
                } ,
                    modifier = Modifier.weight(1f)) {
                    Icon(Icons.Default.Home , contentDescription = "home", modifier = Modifier.size(35.dp),
                        tint =
                        if(selected.value == Icons.Default.Home) Color.White else Color.DarkGray)
                }
                Box(modifier = Modifier
                    .weight(1f)
                    .padding(16.dp), contentAlignment = Alignment.Center) {
                    FloatingActionButton(onClick = {
                        selectedTabIndex.value = -1
                        selected.value = Icons.Default.AddCircle
                        navController.navigate(Screens.Browse.screen){
                            popUpTo(0)
                        }
                    }) {
                        Icon(Icons.Default.Add, contentDescription = "browse", modifier = Modifier.size(35.dp) ,
                            tint = if(selected.value == Icons.Default.Add) Color.White else Color.DarkGray)
                    }
                }
                IconButton(onClick = {
                    selectedTabIndex.value = -1
                    selected.value = Icons.Default.DateRange
                    navController.navigate(Screens.Timeline.screen){
                        popUpTo(0)
                    }
                } ,
                    modifier = Modifier.weight(1f)) {
                    Icon(Icons.Default.DateRange , contentDescription = "timeline", modifier = Modifier.size(35.dp),
                        tint = if(selected.value == Icons.Default.DateRange) Color.White else Color.DarkGray)
                }
            }
        }
    ) {paddingValues ->
        NavHost(navController = navController, startDestination = Screens.Home.screen,
            modifier = Modifier.padding(paddingValues)){
            composable(Screens.Home.screen){ HomeScreen(navController, viewModel = HomeViewModel(application = applicationContext) ,selectedBook, selectedTabIndex )}
            composable(Screens.Browse.screen){ BrowseScreen(viewModel = BrowseViewModel(application = applicationContext)) }
            composable(Screens.Timeline.screen){ TimelineScreen(viewModel = TimelineViewModel(application = applicationContext)) }
            composable(Screens.Details.screen){ DetailsScreen(selectedBook = selectedBook, viewModel = DetailsViewModel(application = applicationContext,  repository = repository) )}
            composable(Screens.New.screen){ TabNewScreen(navController, selectedTabIndex, viewModel = TabNewViewModel(application = applicationContext), selectedBook) }
            composable(Screens.Reading.screen){ TabReadingScreen(navController, selectedTabIndex, viewModel = TabReadingViewModel(application = applicationContext), selectedBook ) }
            composable(Screens.Finished.screen){ TabFinishedScreen(navController, selectedTabIndex, viewModel = TabFinishedViewModel(application = applicationContext), selectedBook) }


        }

    }
}