package com.example.shelfy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.room.Room
import com.example.shelfy.db.AppDatabase
import com.example.shelfy.navigation.AppNavbar
import com.example.shelfy.ui.theme.ShelfyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "shelfy_database").build()

        setContent {
            ShelfyTheme {
                AppNavbar()
            }
        }
    }
}