package com.avci.mote.modules.createnote.data.repository

import com.avci.mote.database.dao.NoteHeadingDao
import com.avci.mote.modules.createnote.data.mapper.NoteHeadingComponentDTOMapper
import com.avci.mote.modules.createnote.domain.repository.NoteHeadingComponentRepository

class NoteHeadingComponentRepositoryImpl(
    private val noteHeadingDao: NoteHeadingDao,
    private val noteHeadingComponentDTOMapper: NoteHeadingComponentDTOMapper,
) : NoteHeadingComponentRepository {

    override suspend fun insertNoteHeadingComponent(noteId: Int, order: Int): Long {
        val noteTextAreaEntity = noteHeadingComponentDTOMapper.mapToNew(noteId = noteId, order = order)
        return noteHeadingDao.insertNoteHeading(noteTextAreaEntity)
    }

    override suspend fun deleteNoteHeadingComponent(componentId: Int) {
        noteHeadingDao.deleteNoteHeading(componentId)
    }

    override suspend fun getHeadingHighestOrder(noteId: Int): Int? {
        return noteHeadingDao.getHeadingHighestOrder(noteId)
    }

    override suspend fun updateHeadingText(componentId: Int, newText: String?) {
        noteHeadingDao.updateHeadingText(componentId = componentId, newText = newText)
    }

    override suspend fun updateHeadingOrder(componentId: Int, newOrder: Int) {
        noteHeadingDao.updateHeadingOrder(componentId = componentId, newOrder = newOrder)
    }
}
