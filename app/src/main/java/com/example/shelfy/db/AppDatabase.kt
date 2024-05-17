package com.example.shelfy.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.migration.Migration
import java.util.concurrent.Executors

@Database(entities = [Shelf::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun shelfDao(): ShelfDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        val databaseWriteExecutor = Executors.newFixedThreadPool(2)

        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Create the new table with the updated schema
                database.execSQL(
                    "CREATE TABLE IF NOT EXISTS Shelf_new (" +
                            "id INTEGER PRIMARY KEY NOT NULL," +
                            "title TEXT," +
                            "author TEXT," +
                            "description TEXT," +
                            "imageUrl TEXT," +
                            "status TEXT," +
                            "rating INTEGER," +
                            "notes TEXT," +
                            "finishedAt INTEGER" + // Assuming finishedDate is stored as a Unix timestamp
                            ")"
                )

                // Copy data from the old table to the new one
                database.execSQL(
                    "INSERT INTO Shelf_new (id, title, author, description, imageUrl, status, rating, notes, finishedAt) " +
                            "SELECT id, title, author, description, imageUrl, status, rating, notes, finishedAt " +
                            "FROM shelf"
                )

                // Drop the old table
                database.execSQL("DROP TABLE shelf")

                // Rename the new table to the original table name
                database.execSQL("ALTER TABLE Shelf_new RENAME TO shelf")
            }
        }

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val db = Room.databaseBuilder(context, AppDatabase::class.java, "db")
                    .addMigrations(MIGRATION_2_3)
                    .build()
                INSTANCE = db
                db
            }
        }
    }
}
