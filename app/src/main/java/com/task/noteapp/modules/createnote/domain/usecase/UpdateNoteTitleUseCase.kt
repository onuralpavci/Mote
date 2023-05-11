package com.task.noteapp.modules.createnote.domain.usecase

import com.task.noteapp.modules.notes.domain.repository.NotesRepository
import javax.inject.Inject
import javax.inject.Named

class UpdateNoteTitleUseCase @Inject constructor(
    @Named(NotesRepository.NOTES_REPOSITORY_INJECTION_NAME)
    private val notesRepository: NotesRepository
) {
    suspend operator fun invoke(title: String?, noteId: Int) {
        return notesRepository.updateNoteTitle(noteId = noteId, newTitle = title)
    }
}
