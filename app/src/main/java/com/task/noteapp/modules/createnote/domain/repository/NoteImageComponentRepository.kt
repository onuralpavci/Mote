package com.task.noteapp.modules.createnote.domain.repository

interface NoteImageComponentRepository {

    suspend fun insertNoteImageComponent(noteId: Int, order: Int): Long

    suspend fun deleteNoteImageComponent(componentId: Int)

    suspend fun updateNoteImageUri(componentId: Int, newUri: String?)

    suspend fun getImageHighestOrder(noteId: Int): Int?


    companion object {
        const val NOTE_IMAGE_COMPONENT_REPOSITORY_INJECTION_NAME = "noteImageComponentRepositoryInjectionName"
    }
}
