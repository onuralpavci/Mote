package com.task.noteapp.modules.createnote.data.di

import com.task.noteapp.database.dao.NoteImageDao
import com.task.noteapp.database.dao.NoteTextAreaDao
import com.task.noteapp.modules.createnote.data.mapper.NoteImageComponentDTOMapper
import com.task.noteapp.modules.createnote.data.mapper.NoteTextAreaComponentDTOMapper
import com.task.noteapp.modules.createnote.data.repository.NoteImageComponentRepositoryImpl
import com.task.noteapp.modules.createnote.data.repository.NoteTextAreaComponentRepositoryImpl
import com.task.noteapp.modules.createnote.domain.repository.NoteImageComponentRepository
import com.task.noteapp.modules.createnote.domain.repository.NoteImageComponentRepository.Companion.NOTE_IMAGE_COMPONENT_REPOSITORY_INJECTION_NAME
import com.task.noteapp.modules.createnote.domain.repository.NoteTextAreaComponentRepository
import com.task.noteapp.modules.createnote.domain.repository.NoteTextAreaComponentRepository.Companion.NOTE_TEXT_AREA_COMPONENT_REPOSITORY_INJECTION_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object CreateNoteNetworkModule {
    @Provides
    @Named(NOTE_IMAGE_COMPONENT_REPOSITORY_INJECTION_NAME)
    fun provideNoteImageComponentRepository(
        noteImageDao: NoteImageDao,
        noteImageComponentDTOMapper: NoteImageComponentDTOMapper
    ): NoteImageComponentRepository {
        return NoteImageComponentRepositoryImpl(
            noteImageDao = noteImageDao,
            noteImageComponentDTOMapper = noteImageComponentDTOMapper
        )
    }

    @Provides
    @Named(NOTE_TEXT_AREA_COMPONENT_REPOSITORY_INJECTION_NAME)
    fun provideNoteTextAreaComponentRepository(
        textAreaDao: NoteTextAreaDao,
        noteTextAreaComponentDTOMapper: NoteTextAreaComponentDTOMapper
    ): NoteTextAreaComponentRepository {
        return NoteTextAreaComponentRepositoryImpl(
            noteTextAreaDao = textAreaDao,
            noteTextAreaComponentDTOMapper = noteTextAreaComponentDTOMapper
        )
    }
}
