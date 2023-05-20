package com.avci.mote.modules.createnote.domain.usecase

import com.avci.mote.modules.createnote.domain.repository.NoteHeadingComponentRepository
import com.avci.mote.modules.createnote.domain.repository.NoteHeadingComponentRepository.Companion.NOTE_HEADING_COMPONENT_REPOSITORY_INJECTION_NAME
import javax.inject.Inject
import javax.inject.Named

class CreateNewNoteHeadingUseCase @Inject constructor(
    @Named(NOTE_HEADING_COMPONENT_REPOSITORY_INJECTION_NAME)
    private val noteHeadingComponentRepository: NoteHeadingComponentRepository,
    private val getNoteComponentHighestOrderUseCase: GetNoteComponentHighestOrderUseCase
) {
    suspend operator fun invoke(noteId: Int): Long {
        val newItemOrder = getNoteComponentHighestOrderUseCase.invoke(noteId) + 1
        return noteHeadingComponentRepository.insertNoteHeadingComponent(noteId = noteId, order = newItemOrder)
    }
}
