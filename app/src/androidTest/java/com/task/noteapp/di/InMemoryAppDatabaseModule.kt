package com.task.noteapp.di

import android.app.Application
import androidx.room.Room
import com.task.noteapp.database.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppDatabaseModule::class]
)
class InMemoryAppDatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room.inMemoryDatabaseBuilder(
            application,
            AppDatabase::class.java
        ).build()
    }
}