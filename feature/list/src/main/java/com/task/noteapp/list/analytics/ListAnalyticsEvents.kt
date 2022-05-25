package com.task.noteapp.list.analytics

import com.task.noteapp.analytics.AnalyticsEvent
import com.task.noteapp.note.Note

sealed class ListAnalyticsEvents(override val event: String): AnalyticsEvent {
    override val screen: String = "List"

    class ViewNotes(notes: List<Note>): ListAnalyticsEvents(event = "Viewed list size = ${notes.size}")
}