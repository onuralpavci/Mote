package com.avci.mote.modules.createnote.domain.usecase

import com.avci.mote.modules.createnote.domain.repository.NoteImageComponentRepository
import com.avci.mote.modules.createnote.domain.repository.NoteImageComponentRepository.Companion.NOTE_IMAGE_COMPONENT_REPOSITORY_INJECTION_NAME
import javax.inject.Inject
import javax.inject.Named

class CreateNewNoteImageUseCase @Inject constructor(
    @Named(NOTE_IMAGE_COMPONENT_REPOSITORY_INJECTION_NAME)
    private val noteImageComponentRepository: NoteImageComponentRepository,
    private val getNoteComponentHighestOrderUseCase: GetNoteComponentHighestOrderUseCase
) {
    suspend operator fun invoke(noteId: Int): Long {
        val newItemOrder = getNoteComponentHighestOrderUseCase.invoke(noteId) + 1
        return noteImageComponentRepository.insertNoteImageComponent(noteId = noteId, order = newItemOrder)
    }
}
