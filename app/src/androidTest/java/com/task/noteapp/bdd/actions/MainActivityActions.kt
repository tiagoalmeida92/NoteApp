package com.task.noteapp.bdd.actions

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.task.noteapp.R

class MainActivityActions {

    fun clicksNewNote() {
        onView(withId(R.id.list_create))
            .perform(click())
    }

    fun clicksFirstNote() {
        onView(withId(R.id.item_note_title))
            .perform(click())
    }
}