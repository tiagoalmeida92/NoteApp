package com.tiago.feature.navigation

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import com.task.noteapp.note.Note

object Navigator {

    private const val addEditActivityClassName = "com.task.noteapp.feature.add.edit.AddEditActivity"
    const val EXTRA_NOTE = "EXTRA_NOTE"

    fun goToAddEditNote(activity: Activity, sharedElement: View, note: Note?) {
        val transitionName = "sharedElement"
        sharedElement.transitionName = transitionName
        val intent = Intent(activity, Class.forName(addEditActivityClassName))
        intent.putExtra(EXTRA_NOTE, note)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            activity,
            sharedElement,
            transitionName
        )
        activity.startActivity(intent, options.toBundle())
    }

}