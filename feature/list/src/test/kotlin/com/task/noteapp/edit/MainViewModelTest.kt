package com.task.noteapp.edit

import com.task.noteapp.Note
import com.task.noteapp.NoteDataSource
import com.task.noteapp.list.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.threeten.bp.OffsetDateTime

class MainViewModelTest {

    private val noteDataSource: NoteDataSource = mock()
    private lateinit var viewModel: MainViewModel
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @Test
    fun `WHEN viewmodel init THEN datasource getAll is called`() = runTest(dispatcher) {
        val items = listOf(aNote(), aNote())
        given(noteDataSource.getAll()).willReturn(flowOf(items))
        viewModel = MainViewModel(noteDataSource, dispatcher)

        advanceUntilIdle()
        assertThat(viewModel.items.value.size).isEqualTo(items.size)
        verify(noteDataSource).getAll()
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