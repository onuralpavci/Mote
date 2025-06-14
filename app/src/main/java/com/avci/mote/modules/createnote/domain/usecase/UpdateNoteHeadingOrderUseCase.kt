package com.avci.mote.modules.createnote.domain.usecase

import com.avci.mote.modules.createnote.domain.repository.NoteHeadingComponentRepository
import com.avci.mote.modules.createnote.domain.repository.NoteHeadingComponentRepository.Companion.NOTE_HEADING_COMPONENT_REPOSITORY_INJECTION_NAME
import javax.inject.Inject
import javax.inject.Named

class UpdateNoteHeadingOrderUseCase @Inject constructor(
    @Named(NOTE_HEADING_COMPONENT_REPOSITORY_INJECTION_NAME)
    private val noteHeadingComponentRepository: NoteHeadingComponentRepository
) {
    suspend operator fun invoke(componentId: Int, newOrder: Int) {
        return noteHeadingComponentRepository.updateHeadingOrder(componentId = componentId, newOrder = newOrder)
    }
}
