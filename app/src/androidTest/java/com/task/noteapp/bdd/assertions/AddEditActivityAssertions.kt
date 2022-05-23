package com.task.noteapp.bdd.assertions

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.task.noteapp.R
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matchers.not

class AddEditActivityAssertions {

    fun seesAddMode() {
        onView(withId(R.id.add_edit_title))
            .check(matches(allOf(isDisplayed(), withText(""))))

        onView(withId(R.id.add_edit_description))
            .check(matches(allOf(isDisplayed(), withText(""))))

        onView(withId(R.id.add_edit_image_url))
            .check(matches(allOf(isDisplayed(), withText(""))))

        onView(withId(R.id.add_edit_save))
            .check(matches(isDisplayed()))

        onView(withId(R.id.add_edit_delete))
            .check(matches(not(isDisplayed())))
    }

    fun seesEditMode(title: String, description: String) {
        onView(withId(R.id.add_edit_title))
            .check(matches(withText(title)))

        onView(withId(R.id.add_edit_description))
            .check(matches(withText(description)))

        onView(withId(R.id.add_edit_image_url))
            .check(matches(isDisplayed()))

        onView(withId(R.id.add_edit_save))
            .check(matches(isDisplayed()))

        onView(withId(R.id.add_edit_delete))
            .check(matches(isDisplayed()))
    }

    fun seesError() {
        onView(withText(R.string.feature_add_edit_error_save))
            .check(matches(isDisplayed()))
    }
}
