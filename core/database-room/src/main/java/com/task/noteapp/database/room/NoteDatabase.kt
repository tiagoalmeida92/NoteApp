package com.task.noteapp.database.room

import com.task.noteapp.Note
import com.task.noteapp.NoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomNoteDataSource(
    private val noteDao: NoteDao
) : NoteDataSource {

    private val converter by lazy { NoteConverter() }

    override fun getAll(): Flow<List<Note>> {
        return noteDao.getAll()
            .map { dbItems -> dbItems.map { converter.parse(it) } }
    }

    override fun update(note: Note) {
        noteDao.update(converter.compose(note))
    }

    override fun delete(note: Note) {
        noteDao.delete(converter.compose(note))
    }

    override fun insert(note: Note) {
        noteDao.insert(converter.compose(note))
    }
}