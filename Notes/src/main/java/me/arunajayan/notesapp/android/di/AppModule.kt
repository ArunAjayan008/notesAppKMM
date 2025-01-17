package me.arunajayan.notesapp.android.di

import android.app.Application
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.arunajayan.notesapp.data.local.DatabaseDriverFactory
import me.arunajayan.notesapp.data.note.SqlDelightNoteDataSource
import me.arunajayan.notesapp.database.NoteDatabase
import me.arunajayan.notesapp.domain.note.NoteDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSqlDriver(app: Application): SqlDriver {
        return DatabaseDriverFactory(app).createDriver()
    }

    @Provides
    @Singleton
    fun provideNoteDataSource(driver: SqlDriver): NoteDataSource {
        return SqlDelightNoteDataSource(NoteDatabase(driver))
    }
}