package com.task.noteapp

import kotlinx.coroutines.flow.Flow

interface NoteDataSource {

    fun getAll(): Flow<List<Note>>
    fun update(note: Note)
    fun insert(note: Note)
    fun delete(note: Note)
}