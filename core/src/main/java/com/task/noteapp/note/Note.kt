package com.task.noteapp.note

import org.threeten.bp.OffsetDateTime
import java.io.Serializable

data class Note(
    val id: Int = 0,
    val title: String,
    val description: String,
    val imageUrl: String?,
    val created: OffsetDateTime,
    val isEdited: Boolean,
) : Serializable