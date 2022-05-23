package com.task.noteapp.di

import com.task.noteapp.NoteDataSource
import com.task.noteapp.database.room.AppDatabase
import com.task.noteapp.database.room.NoteDao
import com.task.noteapp.database.room.RoomNoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NoteDatabaseModule {

    @Singleton
    @Provides
    fun provideDao(appDatabase: AppDatabase): NoteDao {
        return appDatabase.noteDao()
    }

    @Singleton
    @Provides
    fun provideNoteDatabase(dao: NoteDao): NoteDataSource {
        return RoomNoteDataSource(dao)
    }
}