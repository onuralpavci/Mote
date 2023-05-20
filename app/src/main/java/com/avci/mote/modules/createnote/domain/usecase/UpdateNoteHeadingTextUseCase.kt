package com.avci.mote.modules.createnote.domain.usecase

import com.avci.mote.modules.createnote.domain.repository.NoteHeadingComponentRepository
import com.avci.mote.modules.createnote.domain.repository.NoteHeadingComponentRepository.Companion.NOTE_HEADING_COMPONENT_REPOSITORY_INJECTION_NAME
import javax.inject.Inject
import javax.inject.Named

class UpdateNoteHeadingTextUseCase @Inject constructor(
    @Named(NOTE_HEADING_COMPONENT_REPOSITORY_INJECTION_NAME)
    private val noteHeadingComponentRepository: NoteHeadingComponentRepository
) {
    suspend operator fun invoke(componentId: Int, newText: String?) {
        return noteHeadingComponentRepository.updateHeadingText(componentId = componentId, newText = newText)
    }
}
