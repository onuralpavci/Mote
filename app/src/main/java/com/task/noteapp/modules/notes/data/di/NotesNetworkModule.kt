package com.task.noteapp.modules.notes.data.di

import com.task.noteapp.database.dao.NoteDao
import com.task.noteapp.database.dao.NoteImageDao
import com.task.noteapp.database.dao.NoteTextAreaDao
import com.task.noteapp.modules.createnote.data.mapper.NoteDTOMapper
import com.task.noteapp.modules.createnote.data.mapper.NoteImageComponentDTOMapper
import com.task.noteapp.modules.createnote.data.mapper.NoteTextAreaComponentDTOMapper
import com.task.noteapp.modules.notes.data.repository.NotesRepositoryImpl
import com.task.noteapp.modules.notes.domain.repository.NotesRepository
import com.task.noteapp.modules.notes.domain.repository.NotesRepository.Companion.NOTES_REPOSITORY_INJECTION_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object NotesNetworkModule {

    @Provides
    @Named(NOTES_REPOSITORY_INJECTION_NAME)
    fun provideNotesRepository(
        noteDao: NoteDao,
        noteTextAreaDao: NoteTextAreaDao,
        noteImageDao: NoteImageDao,
        noteDTOMapper: NoteDTOMapper,
        noteTextAreaComponentDTOMapper: NoteTextAreaComponentDTOMapper,
        noteImageComponentDTOMapper: NoteImageComponentDTOMapper
    ): NotesRepository {
        return NotesRepositoryImpl(
            noteDao = noteDao,
            noteTextAreaDao = noteTextAreaDao,
            noteImageDao = noteImageDao,
            noteDTOMapper = noteDTOMapper,
            noteTextAreaComponentDTOMapper = noteTextAreaComponentDTOMapper,
            noteImageComponentDTOMapper = noteImageComponentDTOMapper
        )
    }
}
