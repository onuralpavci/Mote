package com.avci.mote.modules.createnote.domain.usecase

import com.avci.mote.modules.notes.domain.repository.NotesRepository
import java.util.Date
import javax.inject.Inject
import javax.inject.Named

class UpdateNoteLastEditDateUseCase @Inject constructor(
    @Named(NotesRepository.NOTES_REPOSITORY_INJECTION_NAME)
    private val notesRepository: NotesRepository
) {
    suspend operator fun invoke(noteId: Int) {
        return notesRepository.updateNoteLastEditTimeStamp(noteId, Date().time)
    }
}
