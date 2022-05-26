package com.task.noteapp.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.noteapp.analytics.Analytics
import com.task.noteapp.list.analytics.ListAnalyticsEvents
import com.task.noteapp.note.Note
import com.task.noteapp.note.NoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataSource: NoteDataSource,
    private val dispatcher: CoroutineDispatcher,
    private val analytics: Analytics
) : ViewModel() {

    private val _items = dataSource.getAll()
        .onEach {
            analytics.track(ListAnalyticsEvents.ViewNotes(it))
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val items: StateFlow<List<Note>> = _items

}