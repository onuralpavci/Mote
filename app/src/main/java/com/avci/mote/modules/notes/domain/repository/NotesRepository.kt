package com.avci.mote.modules.notes.domain.repository

import com.avci.mote.modules.createnote.domain.model.BaseNoteComponentDTO
import com.avci.mote.modules.createnote.domain.model.NoteDTO
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

    suspend fun getNotesDTOFlow(): Flow<List<NoteDTO>>

    suspend fun getAllNoteComponentsDTOFlow(): Flow<List<BaseNoteComponentDTO>>

    suspend fun getNoteDTO(noteId: Int): NoteDTO?

    suspend fun getNoteDTOFlow(noteId: Int): Flow<NoteDTO?>

    suspend fun getNoteComponentsDTOFlow(noteId: Int): Flow<List<BaseNoteComponentDTO>>

    suspend fun insertNote(createTimeStamp: Long): Int

    suspend fun deleteNote(nodeId: Int)

    suspend fun updateNoteTitle(noteId: Int, newTitle: String?)

    suspend fun updateNoteLastEditTimeStamp(noteId: Int, lastEditTimeStamp: Long)

    suspend fun updateNoteIsSaved(noteId: Int, isSaved: Boolean)

    companion object {
        const val NOTES_REPOSITORY_INJECTION_NAME = "notesRepositoryInjectionName"
    }
}
