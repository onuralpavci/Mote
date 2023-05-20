package com.avci.mote.modules.core.di

import android.content.Context
import androidx.room.Room
import com.avci.mote.database.NoteDatabase
import com.avci.mote.database.NoteDatabase.Companion.MIGRATION_1_2
import com.avci.mote.database.NoteDatabase.Companion.MIGRATION_2_3
import com.avci.mote.database.dao.NoteDao
import com.avci.mote.database.dao.NoteHeadingDao
import com.avci.mote.database.dao.NoteImageDao
import com.avci.mote.database.dao.NoteTextAreaDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext appContext: Context
    ): NoteDatabase {
        return Room
            .databaseBuilder(appContext, NoteDatabase::class.java, NoteDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .addMigrations(MIGRATION_1_2)
            .addMigrations(MIGRATION_2_3)
            .build()
    }

    @Singleton
    @Provides
    fun provideNoteDao(database: NoteDatabase): NoteDao {
        return database.noteDao()
    }

    @Singleton
    @Provides
    fun provideNoteTextAreaDao(database: NoteDatabase): NoteTextAreaDao {
        return database.noteTextAreaDao()
    }

    @Singleton
    @Provides
    fun provideNoteImageDao(database: NoteDatabase): NoteImageDao {
        return database.noteImageDao()
    }

    @Singleton
    @Provides
    fun provideNoteHeadingDao(database: NoteDatabase): NoteHeadingDao {
        return database.noteHeadingDao()
    }
}
