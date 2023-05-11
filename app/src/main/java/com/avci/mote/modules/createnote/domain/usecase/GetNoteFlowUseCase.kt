package com.avci.mote.modules.createnote.domain.usecase

import com.avci.mote.modules.createnote.domain.mapper.NoteMapper
import com.avci.mote.modules.createnote.domain.model.Note
import com.avci.mote.modules.notes.domain.repository.NotesRepository
import com.avci.mote.modules.notes.domain.repository.NotesRepository.Companion.NOTES_REPOSITORY_INJECTION_NAME
import javax.inject.Inject
import javax.inject.Named
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class GetNoteFlowUseCase @Inject constructor(
    @Named(NOTES_REPOSITORY_INJECTION_NAME)
    private val notesRepository: NotesRepository,
    private val noteMapper: NoteMapper
) {
    suspend operator fun invoke(noteId: Int): Flow<Note?> {
        val noteFlow = notesRepository.getNoteDTOFlow(noteId = noteId)
        val noteComponentFlow = notesRepository.getNoteComponentsDTOFlow(noteId = noteId)
        return noteFlow.combine(noteComponentFlow) { note, noteComponents ->
            if (note == null) {
                null
            } else {
                noteMapper.mapTo(note, noteComponents)
            }
        }
    }
}
