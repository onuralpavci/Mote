package com.avci.mote.modules.createnote.domain.usecase

import com.avci.mote.modules.createnote.domain.repository.NoteImageComponentRepository
import com.avci.mote.modules.createnote.domain.repository.NoteImageComponentRepository.Companion.NOTE_IMAGE_COMPONENT_REPOSITORY_INJECTION_NAME
import com.avci.mote.modules.createnote.domain.repository.NoteTextAreaComponentRepository
import com.avci.mote.modules.createnote.domain.repository.NoteTextAreaComponentRepository.Companion.NOTE_TEXT_AREA_COMPONENT_REPOSITORY_INJECTION_NAME
import javax.inject.Inject
import javax.inject.Named

class GetNoteComponentHighestOrderUseCase @Inject constructor(
    @Named(NOTE_IMAGE_COMPONENT_REPOSITORY_INJECTION_NAME)
    private val noteImageComponentRepository: NoteImageComponentRepository,
    @Named(NOTE_TEXT_AREA_COMPONENT_REPOSITORY_INJECTION_NAME)
    private val noteTextAreaComponentRepository: NoteTextAreaComponentRepository
) {
    suspend operator fun invoke(noteId: Int): Int {
        return maxOf<Int>(
            noteTextAreaComponentRepository.getTextAreaHighestOrder(noteId) ?: DEFAULT_NOTE_COMPONENT_ORDER,
            noteImageComponentRepository.getImageHighestOrder(noteId) ?: DEFAULT_NOTE_COMPONENT_ORDER
        )
    }

    companion object {
        const val DEFAULT_NOTE_COMPONENT_ORDER = 0
    }
}
