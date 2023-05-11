package com.task.noteapp.modules.createnote.domain.usecase

import com.task.noteapp.modules.notes.domain.repository.NotesRepository
import com.task.noteapp.modules.notes.domain.repository.NotesRepository.Companion.NOTES_REPOSITORY_INJECTION_NAME
import javax.inject.Inject
import javax.inject.Named

class DeleteNoteUseCase @Inject constructor(
    @Named(NOTES_REPOSITORY_INJECTION_NAME)
    private val notesRepository: NotesRepository
) {
    suspend operator fun invoke(noteId: Int) {
        return notesRepository.deleteNote(noteId)
    }
}
