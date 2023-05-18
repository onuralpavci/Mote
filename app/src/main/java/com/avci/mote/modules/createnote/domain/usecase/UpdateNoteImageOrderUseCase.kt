package com.avci.mote.modules.createnote.domain.usecase

import com.avci.mote.modules.createnote.domain.repository.NoteImageComponentRepository
import com.avci.mote.modules.createnote.domain.repository.NoteImageComponentRepository.Companion.NOTE_IMAGE_COMPONENT_REPOSITORY_INJECTION_NAME
import javax.inject.Inject
import javax.inject.Named

class UpdateNoteImageOrderUseCase @Inject constructor(
    @Named(NOTE_IMAGE_COMPONENT_REPOSITORY_INJECTION_NAME)
    private val noteImageComponentRepository: NoteImageComponentRepository
) {
    suspend operator fun invoke(componentId: Int, newOrder: Int) {
        return noteImageComponentRepository.updateNoteImageOrder(componentId = componentId, newOrder = newOrder)
    }
}
