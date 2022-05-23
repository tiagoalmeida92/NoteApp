package com.task.noteapp

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.task.noteapp.bdd.Then
import com.task.noteapp.bdd.When
import com.task.noteapp.list.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@LargeTest
class NotesTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val rule = ActivityScenarioRule(MainActivity::class.java)

    val `when` = When()
    val then = Then()

    @After
    fun cleanup() {
        rule.scenario.close()
    }

    @Test
    fun clickNewNoteButton_opensAddEditScreen() {
        `when`.user.onMainScreen()
            .clicksNewNote()

        then.user.onAddEditScreen()
            .seesAddMode()
    }

    @Test
    fun savingNewNote_appearsOnList() {
        val title = "Title"
        val description = "Description"

        `when`.user.onMainScreen()
            .clicksNewNote()

        `when`.user.onAddEditScreen()
            .writeFields(title, description)
            .save()

        then.user.onMainScreen()
            .seesNote(title, description)
            .doesNotSeeImage()
            .doesNotSeeEdited()
    }

    @Test
    fun savingNewNoteWithImage_appearsOnList() {
        val title = "Title"
        val description = "Description"
        val imageUrl = "https://i.imgur.com/8i4dcRG.jpg"

        `when`.user.onMainScreen()
            .clicksNewNote()

        `when`.user.onAddEditScreen()
            .writeFields(title, description, imageUrl)
            .save()

        then.user.onMainScreen()
            .seesNote(title, description)
            .seesImage()
            .doesNotSeeEdited()
    }

    @Test
    fun savingNewNoteWithoutTitle_showsError() {
        val title = ""
        val description = "Description"

        `when`.user.onMainScreen()
            .clicksNewNote()

        `when`.user.onAddEditScreen()
            .writeFields(title, description)
            .save()

        then.user.onAddEditScreen()
            .seesError()
    }

    @Test
    fun clickNote_opensAddEdit() {
        val title = "Title"
        val description = "Description"

        `when`.user.onMainScreen()
            .clicksNewNote()

        `when`.user.onAddEditScreen()
            .writeFields(title, description)
            .save()

        `when`.user.onMainScreen()
            .clicksFirstNote()

        then.user.onAddEditScreen()
            .seesEditMode(title, description)
    }

    @Test
    fun updateNote_seesNoteEdited() {
        val title = "Title"
        val description = "Description"
        val updatedTitle = "Title 2"

        `when`.user.onMainScreen()
            .clicksNewNote()

        `when`.user.onAddEditScreen()
            .writeFields(title, description)
            .save()

        `when`.user.onMainScreen()
            .clicksFirstNote()

        `when`.user.onAddEditScreen()
            .writeFields(updatedTitle, description)
            .save()

        then.user.onMainScreen()
            .seesNote(updatedTitle, description)
            .seesEdited()
    }

    @Test
    fun deleteNote_thenNoteRemoved() {
        val title = "Title"
        val description = "Description"

        `when`.user.onMainScreen()
            .clicksNewNote()

        `when`.user.onAddEditScreen()
            .writeFields(title, description)
            .save()

        `when`.user.onMainScreen()
            .clicksFirstNote()

        `when`.user.onAddEditScreen()
            .delete()

        then.user.onMainScreen()
            .doesNotSeeNotes()
    }
}