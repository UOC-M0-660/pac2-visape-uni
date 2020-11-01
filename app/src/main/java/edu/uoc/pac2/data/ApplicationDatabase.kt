package edu.uoc.pac2.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Room Application Database
 */

@Database(entities = arrayOf(Book::class), version = 1)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao

    companion object {
        @Volatile
        private var INSTANCE: ApplicationDatabase? = null

        fun getDatabase(context: Context) : ApplicationDatabase {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        ApplicationDatabase::class.java,
                        "book_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}