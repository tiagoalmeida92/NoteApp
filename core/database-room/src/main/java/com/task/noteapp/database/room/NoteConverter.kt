package com.task.noteapp.database.room

import com.task.noteapp.Note
import com.task.noteapp.database.room.model.NoteRoom

class NoteConverter {

    fun parse(dbNote: NoteRoom): Note {
        return Note(
            dbNote.id,
            dbNote.title,
            dbNote.description,
            dbNote.imageUrl,
            dbNote.created,
            dbNote.isEdited
        )
    }

    fun compose(note: Note): NoteRoom {
        return NoteRoom(
            note.id,
            note.title,
            note.description,
            note.imageUrl,
            note.created,
            note.isEdited
        )
    }

}
