package com.avci.mote.modules.notes.data.di

import com.avci.mote.database.dao.NoteDao
import com.avci.mote.database.dao.NoteHeadingDao
import com.avci.mote.database.dao.NoteImageDao
import com.avci.mote.database.dao.NoteTextAreaDao
import com.avci.mote.modules.createnote.data.mapper.NoteDTOMapper
import com.avci.mote.modules.createnote.data.mapper.NoteHeadingComponentDTOMapper
import com.avci.mote.modules.createnote.data.mapper.NoteImageComponentDTOMapper
import com.avci.mote.modules.createnote.data.mapper.NoteTextAreaComponentDTOMapper
import com.avci.mote.modules.notes.data.repository.NotesRepositoryImpl
import com.avci.mote.modules.notes.domain.repository.NotesRepository
import com.avci.mote.modules.notes.domain.repository.NotesRepository.Companion.NOTES_REPOSITORY_INJECTION_NAME
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
        noteHeadingDao: NoteHeadingDao,
        noteImageDao: NoteImageDao,
        noteDTOMapper: NoteDTOMapper,
        noteTextAreaComponentDTOMapper: NoteTextAreaComponentDTOMapper,
        noteHeadingComponentDTOMapper: NoteHeadingComponentDTOMapper,
        noteImageComponentDTOMapper: NoteImageComponentDTOMapper
    ): NotesRepository {
        return NotesRepositoryImpl(
            noteDao = noteDao,
            noteTextAreaDao = noteTextAreaDao,
            noteHeadingDao = noteHeadingDao,
            noteImageDao = noteImageDao,
            noteDTOMapper = noteDTOMapper,
            noteTextAreaComponentDTOMapper = noteTextAreaComponentDTOMapper,
            noteHeadingComponentDTOMapper = noteHeadingComponentDTOMapper,
            noteImageComponentDTOMapper = noteImageComponentDTOMapper
        )
    }
}
