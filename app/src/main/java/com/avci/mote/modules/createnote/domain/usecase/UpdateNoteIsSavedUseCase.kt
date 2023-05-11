package com.avci.mote.modules.createnote.domain.usecase

import com.avci.mote.modules.notes.domain.repository.NotesRepository
import com.avci.mote.modules.notes.domain.repository.NotesRepository.Companion.NOTES_REPOSITORY_INJECTION_NAME
import javax.inject.Inject
import javax.inject.Named

class UpdateNoteIsSavedUseCase @Inject constructor(
    @Named(NOTES_REPOSITORY_INJECTION_NAME)
    private val notesRepository: NotesRepository
) {
    suspend operator fun invoke(noteId: Int, isSaved: Boolean) {
        return notesRepository.updateNoteIsSaved(noteId = noteId, isSaved = isSaved)
    }
}
