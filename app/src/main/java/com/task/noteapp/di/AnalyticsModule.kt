package com.task.noteapp.di

import com.task.noteapp.analytics.Analytics
import com.task.noteapp.database.room.AppDatabase
import com.task.noteapp.database.room.NoteDao
import com.task.noteapp.log.AnalyticsLog
import com.task.noteapp.log.LogcatLog
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AnalyticsModule {

    @Singleton
    @Provides
    fun provideAnalytics(): Analytics {
        return AnalyticsLog(LogcatLog)
    }

}