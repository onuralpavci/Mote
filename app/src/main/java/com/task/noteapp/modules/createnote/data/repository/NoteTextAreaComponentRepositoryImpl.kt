package com.task.noteapp.modules.createnote.data.repository

import com.task.noteapp.database.dao.NoteTextAreaDao
import com.task.noteapp.modules.createnote.data.mapper.NoteTextAreaComponentDTOMapper
import com.task.noteapp.modules.createnote.domain.repository.NoteTextAreaComponentRepository

class NoteTextAreaComponentRepositoryImpl(
    private val noteTextAreaDao: NoteTextAreaDao,
    private val noteTextAreaComponentDTOMapper: NoteTextAreaComponentDTOMapper,
) : NoteTextAreaComponentRepository {

    override suspend fun insertNoteTextAreaComponent(noteId: Int, order: Int): Long {
        val noteTextAreaEntity = noteTextAreaComponentDTOMapper.mapToNew(noteId = noteId, order = order)
        return noteTextAreaDao.insertNoteTextArea(noteTextAreaEntity)
    }

    override suspend fun deleteNoteTextAreaComponent(componentId: Int) {
        noteTextAreaDao.deleteNoteTextArea(componentId)
    }

    override suspend fun getTextAreaHighestOrder(noteId: Int): Int? {
        return noteTextAreaDao.getTextAreaHighestOrder(noteId)
    }

    override suspend fun updateTextAreaText(componentId: Int, newText: String?) {
        noteTextAreaDao.updateTextAreaText(componentId = componentId, newText = newText)
    }
}
