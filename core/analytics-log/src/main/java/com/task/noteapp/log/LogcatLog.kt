package com.task.noteapp.log

import android.util.Log

object LogcatLog : LogService {
    override fun log(message: String) {
        Log.d("NoteApp Log", message)
    }
}