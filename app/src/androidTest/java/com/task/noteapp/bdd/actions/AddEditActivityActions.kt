package com.task.noteapp.bdd.actions

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.task.noteapp.R

class AddEditActivityActions {
    fun writeFields(title: String, description: String, imageUrl: String = "") = apply {
        onView(withId(R.id.add_edit_title))
            .perform(replaceText(title))

        onView(withId(R.id.add_edit_description))
            .perform(replaceText(description))

        onView(withId(R.id.add_edit_image_url))
            .perform(replaceText(imageUrl))
    }

    fun save() {
        onView(withId(R.id.add_edit_save))
            .perform(ViewActions.click())
    }

    fun delete() {
        onView(withId(R.id.add_edit_delete))
            .perform(ViewActions.click())
    }


}
