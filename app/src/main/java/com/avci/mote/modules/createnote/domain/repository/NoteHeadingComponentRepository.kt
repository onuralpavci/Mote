package com.avci.mote.modules.createnote.domain.repository

interface NoteHeadingComponentRepository {

    suspend fun insertNoteHeadingComponent(noteId: Int, order: Int): Long

    suspend fun deleteNoteHeadingComponent(componentId: Int)

    suspend fun getHeadingHighestOrder(noteId: Int): Int?

    suspend fun updateHeadingText(componentId: Int, newText: String?)

    suspend fun updateHeadingOrder(componentId: Int, newOrder: Int)

    companion object {
        const val NOTE_HEADING_COMPONENT_REPOSITORY_INJECTION_NAME = "noteHeadingComponentRepositoryInjectionName"
    }
}
