package com.task.noteapp.edit

import androidx.lifecycle.SavedStateHandle
import com.task.noteapp.Note
import com.task.noteapp.NoteDataSource
import com.task.noteapp.feature.add.edit.AddEditViewModel
import com.task.noteapp.feature.add.edit.ErrorViewState
import com.tiago.feature.navigation.Navigator.EXTRA_NOTE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.verify
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verifyZeroInteractions
import org.threeten.bp.OffsetDateTime

class AddEditViewModelTest {

    private val savedStateHandle = SavedStateHandle()
    private val noteDataSource: NoteDataSource = mock()
    private lateinit var viewModel: AddEditViewModel
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @Test
    fun `WHEN saving with title empty THEN error emitted`() {
        viewModel = AddEditViewModel(SavedStateHandle(), noteDataSource, dispatcher)
        viewModel.save(
            imageUrl = "",
            title = "",
            description = "A description"
        )

        verifyZeroInteractions(noteDataSource)

        assertThat(viewModel.error.value).isEqualTo(ErrorViewState.TitleAndDescriptionError)
    }

    @Test
    fun `WHEN saving with subtitle THEN error emitted`() {
        viewModel = AddEditViewModel(SavedStateHandle(), noteDataSource, dispatcher)
        viewModel.save(
            imageUrl = "",
            title = "A title",
            description = ""
        )

        verifyZeroInteractions(noteDataSource)

        assertThat(viewModel.error.value).isEqualTo(ErrorViewState.TitleAndDescriptionError)
    }

    @Test
    fun `WHEN saving existing note THEN data source update called`() = runTest(dispatcher) {
        savedStateHandle[EXTRA_NOTE] = aNote()
        viewModel = AddEditViewModel(savedStateHandle, noteDataSource, dispatcher)

        viewModel.save(
            imageUrl = "",
            title = "A title",
            description = "A description"
        )
        advanceUntilIdle()

        verify(noteDataSource).update(any())
    }

    @Test
    fun `WHEN saving new note THEN data source update called`() = runTest(dispatcher) {
        viewModel = AddEditViewModel(savedStateHandle, noteDataSource, dispatcher)

        viewModel.save(
            imageUrl = "",
            title = "A title",
            description = "A description"
        )
        advanceUntilIdle()

        verify(noteDataSource).insert(any())
    }

    @Test
    fun `WHEN deleting note THEN data source delete called`() = runTest(dispatcher) {
        savedStateHandle[EXTRA_NOTE] = aNote()
        viewModel = AddEditViewModel(savedStateHandle, noteDataSource, dispatcher)

        viewModel.delete()

        advanceUntilIdle()

        verify(noteDataSource).delete(any())
    }

    private fun aNote(): Note {
        return Note(
            title = "Title",
            description = "Description",
            imageUrl = null,
            created = OffsetDateTime.now(),
            isEdited = false
        )
    }
}