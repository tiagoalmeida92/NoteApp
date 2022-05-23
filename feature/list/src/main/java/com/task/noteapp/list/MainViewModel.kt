package com.task.noteapp.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.noteapp.Note
import com.task.noteapp.NoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataSource: NoteDataSource,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _items = MutableStateFlow<List<Note>>(emptyList())
    val items: StateFlow<List<Note>> = _items

    init {
        viewModelScope.launch {
            withContext(dispatcher) {
                dataSource.getAll()
                    .collect {
                        _items.value = it
                    }
            }
        }

    }

}