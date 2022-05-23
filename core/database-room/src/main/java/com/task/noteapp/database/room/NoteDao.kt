package com.task.noteapp.database.room

import androidx.room.*
import com.task.noteapp.database.room.model.NoteRoom
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDao {

    @Query("SELECT * FROM noteRoom")
    fun getAll(): Flow<List<NoteRoom>>

    @Insert
    fun insert(note: NoteRoom)

    @Update
    fun update(note: NoteRoom)

    @Delete
    fun delete(note: NoteRoom)

}