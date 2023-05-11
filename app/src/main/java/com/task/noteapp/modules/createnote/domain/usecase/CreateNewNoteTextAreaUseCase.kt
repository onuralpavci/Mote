package com.task.noteapp.modules.createnote.domain.usecase

import com.task.noteapp.modules.createnote.domain.repository.NoteTextAreaComponentRepository
import com.task.noteapp.modules.createnote.domain.repository.NoteTextAreaComponentRepository.Companion.NOTE_TEXT_AREA_COMPONENT_REPOSITORY_INJECTION_NAME
import javax.inject.Inject
import javax.inject.Named

class CreateNewNoteTextAreaUseCase @Inject constructor(
    @Named(NOTE_TEXT_AREA_COMPONENT_REPOSITORY_INJECTION_NAME)
    private val noteTextAreaComponentRepository: NoteTextAreaComponentRepository,
    private val getNoteComponentHighestOrderUseCase: GetNoteComponentHighestOrderUseCase
) {
    suspend operator fun invoke(noteId: Int): Long {
        val newItemOrder = getNoteComponentHighestOrderUseCase.invoke(noteId) + 1
        return noteTextAreaComponentRepository.insertNoteTextAreaComponent(noteId = noteId, order = newItemOrder)
    }
}
