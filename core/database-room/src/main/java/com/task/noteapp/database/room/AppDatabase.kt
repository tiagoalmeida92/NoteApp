package com.task.noteapp.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.task.noteapp.database.room.model.NoteRoom

@Database(entities = [NoteRoom::class], version = 3, exportSchema = false)
@TypeConverters(NoteAppTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}