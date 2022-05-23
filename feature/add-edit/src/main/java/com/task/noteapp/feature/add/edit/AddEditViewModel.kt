package com.task.noteapp.feature.add.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.noteapp.Note
import com.task.noteapp.NoteDataSource
import com.tiago.feature.navigation.Navigator.EXTRA_NOTE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.OffsetDateTime
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val noteDataSource: NoteDataSource,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    var note = savedStateHandle.get<Note>(EXTRA_NOTE)

    val updated = MutableStateFlow(false)

    val error = MutableStateFlow(ErrorViewState.NoErrors)

    fun save(
        imageUrl: String,
        title: String,
        description: String
    ) {
        if (title.isEmpty() || description.isEmpty()) {
            error.value = ErrorViewState.TitleAndDescriptionError
            return
        }

        viewModelScope.launch {
            withContext(dispatcher) {
                val note = note
                if (note == null) {
                    noteDataSource.insert(
                        Note(
                            title = title,
                            description = description,
                            imageUrl = imageUrl,
                            created = OffsetDateTime.now(),
                            isEdited = false
                        )
                    )
                } else {
                    noteDataSource.update(
                        note.copy(
                            imageUrl = imageUrl,
                            title = title,
                            description = description,
                            isEdited = true
                        )
                    )
                }
                updated.value = true
            }
        }
    }

    fun delete() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                noteDataSource.delete(note!!)
                updated.value = true
            }
        }
    }

    fun clearError() {
        error.value = ErrorViewState.NoErrors
    }
}

enum class ErrorViewState {
    NoErrors,
    TitleAndDescriptionError
}

