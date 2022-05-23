package com.task.noteapp.bdd.assertions

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.task.noteapp.R
import com.task.noteapp.utils.RecyclerViewItemCountAssertion
import org.hamcrest.CoreMatchers.not

class MainActivityAssertions {
    fun seesNote(title: String, description: String) = apply {
        onView(withId(R.id.item_note_title))
            .check(matches(withText(title)))
        onView(withId(R.id.item_note_description))
            .check(matches(withText(description)))
    }

    fun seesImage() = apply {
        onView(withId(R.id.item_note_image))
            .check(matches(isDisplayed()))
    }

    fun doesNotSeeImage() = apply {
        onView(withId(R.id.item_note_image))
            .check(matches(not(isDisplayed())))
    }

    fun doesNotSeeEdited() {
        onView(withId(R.id.item_note_edited))
            .check(matches(not(isDisplayed())))
    }

    fun seesEdited() {
        onView(withId(R.id.item_note_edited))
            .check(matches(isDisplayed()))
    }

    fun doesNotSeeNotes() {
        onView(withId(R.id.notes_recycler_view)).check(RecyclerViewItemCountAssertion(0))
    }

}
