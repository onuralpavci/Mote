package com.task.noteapp.modules.notes.domain.usecase

import com.task.noteapp.modules.createnote.domain.model.BaseNoteComponent.TextAreaComponent
import com.task.noteapp.modules.createnote.domain.model.Note
import javax.inject.Inject

class DoesNoteContainQueryUseCase @Inject constructor() {
    operator fun invoke(note: Note, query: String): Boolean {
        val textAreaComponents = note.noteComponents.filterIsInstance<TextAreaComponent>()
        val doesTitleContainQuery = note.title?.contains(other = query, ignoreCase = true) == true
        val doesAnyTextComponentContainQuery = textAreaComponents.any { textAreaComponent ->
            textAreaComponent.text?.contains(other = query, ignoreCase = true) == true
        }
        return doesTitleContainQuery || doesAnyTextComponentContainQuery
    }
}
