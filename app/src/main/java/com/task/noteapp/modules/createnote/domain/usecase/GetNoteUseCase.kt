package com.task.noteapp.modules.createnote.domain.usecase

import com.task.noteapp.modules.createnote.domain.mapper.NoteMapper
import com.task.noteapp.modules.createnote.domain.model.Note
import com.task.noteapp.modules.notes.domain.repository.NotesRepository
import com.task.noteapp.modules.notes.domain.repository.NotesRepository.Companion.NOTES_REPOSITORY_INJECTION_NAME
import javax.inject.Inject
import javax.inject.Named

class GetNoteUseCase @Inject constructor(
    @Named(NOTES_REPOSITORY_INJECTION_NAME)
    private val notesRepository: NotesRepository,
    private val noteMapper: NoteMapper
) {
    suspend operator fun invoke(noteId: Int): Note? {
        val noteDTO = notesRepository.getNoteDTO(noteId = noteId)
        val noteComponentDTOList = notesRepository.getNoteComponentsDTO(noteId = noteId)
        return if (noteDTO == null) {
            null
        } else {
            noteMapper.mapTo(noteDTO, noteComponentDTOList)
        }
    }
}
