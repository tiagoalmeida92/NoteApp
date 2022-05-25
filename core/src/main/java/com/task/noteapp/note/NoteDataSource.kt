package com.task.noteapp.note

import kotlinx.coroutines.flow.Flow

interface NoteDataSource {

    fun getAll(): Flow<List<Note>>
    fun update(note: Note)
    fun insert(note: Note)
    fun delete(note: Note)
}