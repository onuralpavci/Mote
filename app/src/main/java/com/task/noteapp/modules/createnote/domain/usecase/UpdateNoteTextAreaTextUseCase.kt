package com.task.noteapp.modules.createnote.domain.usecase

import com.task.noteapp.modules.createnote.domain.repository.NoteTextAreaComponentRepository
import com.task.noteapp.modules.createnote.domain.repository.NoteTextAreaComponentRepository.Companion.NOTE_TEXT_AREA_COMPONENT_REPOSITORY_INJECTION_NAME
import javax.inject.Inject
import javax.inject.Named

class UpdateNoteTextAreaTextUseCase @Inject constructor(
    @Named(NOTE_TEXT_AREA_COMPONENT_REPOSITORY_INJECTION_NAME)
    private val noteTextAreaComponentRepository: NoteTextAreaComponentRepository
) {
    suspend operator fun invoke(componentId: Int, newText: String?) {
        return noteTextAreaComponentRepository.updateTextAreaText(componentId = componentId, newText = newText)
    }
}
