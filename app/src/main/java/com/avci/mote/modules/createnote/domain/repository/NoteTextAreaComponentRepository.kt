package com.avci.mote.modules.createnote.domain.repository

interface NoteTextAreaComponentRepository {

    suspend fun insertNoteTextAreaComponent(noteId: Int, order: Int): Long

    suspend fun deleteNoteTextAreaComponent(componentId: Int)

    suspend fun getTextAreaHighestOrder(noteId: Int): Int?

    suspend fun updateTextAreaText(componentId: Int, newText: String?)

    companion object {
        const val NOTE_TEXT_AREA_COMPONENT_REPOSITORY_INJECTION_NAME = "noteTextAreaComponentRepositoryInjectionName"
    }
}
