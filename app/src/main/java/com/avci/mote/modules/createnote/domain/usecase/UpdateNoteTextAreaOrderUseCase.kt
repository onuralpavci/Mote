package com.avci.mote.modules.createnote.domain.usecase

import com.avci.mote.modules.createnote.domain.repository.NoteTextAreaComponentRepository
import com.avci.mote.modules.createnote.domain.repository.NoteTextAreaComponentRepository.Companion.NOTE_TEXT_AREA_COMPONENT_REPOSITORY_INJECTION_NAME
import javax.inject.Inject
import javax.inject.Named

class UpdateNoteTextAreaOrderUseCase @Inject constructor(
    @Named(NOTE_TEXT_AREA_COMPONENT_REPOSITORY_INJECTION_NAME)
    private val noteTextAreaComponentRepository: NoteTextAreaComponentRepository
) {
    suspend operator fun invoke(componentId: Int, newOrder: Int) {
        return noteTextAreaComponentRepository.updateTextAreaOrder(componentId = componentId, newOrder = newOrder)
    }
}
