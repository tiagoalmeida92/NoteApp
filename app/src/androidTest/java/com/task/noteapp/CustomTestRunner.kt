package com.task.noteapp

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.hilt.android.testing.HiltTestApplication

class CustomTestRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, name: String?, context: Context?): Application {
        AndroidThreeTen.init(context)
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}