package com.task.noteapp.modules.createnote.domain.usecase

import com.task.noteapp.modules.createnote.domain.repository.NoteImageComponentRepository
import com.task.noteapp.modules.createnote.domain.repository.NoteImageComponentRepository.Companion.NOTE_IMAGE_COMPONENT_REPOSITORY_INJECTION_NAME
import javax.inject.Inject
import javax.inject.Named

class UpdateNoteImageUriUseCase @Inject constructor(
    @Named(NOTE_IMAGE_COMPONENT_REPOSITORY_INJECTION_NAME)
    private val noteImageComponentRepository: NoteImageComponentRepository
) {
    suspend operator fun invoke(componentId: Int, newUri: String?) {
        return noteImageComponentRepository.updateNoteImageUri(componentId = componentId, newUri = newUri)
    }
}
