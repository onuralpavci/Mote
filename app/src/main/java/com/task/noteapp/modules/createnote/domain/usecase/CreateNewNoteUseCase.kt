package com.task.noteapp.modules.createnote.domain.usecase

import com.task.noteapp.modules.notes.domain.repository.NotesRepository
import com.task.noteapp.modules.notes.domain.repository.NotesRepository.Companion.NOTES_REPOSITORY_INJECTION_NAME
import java.util.Date
import javax.inject.Inject
import javax.inject.Named

class CreateNewNoteUseCase @Inject constructor(
    @Named(NOTES_REPOSITORY_INJECTION_NAME)
    private val notesRepository: NotesRepository
) {
    suspend operator fun invoke(): Int {
        return notesRepository.insertNote(Date().time)
    }
}
