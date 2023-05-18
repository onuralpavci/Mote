package com.avci.mote.modules.createnote.data.repository

import com.avci.mote.database.dao.NoteImageDao
import com.avci.mote.modules.createnote.data.mapper.NoteImageComponentDTOMapper
import com.avci.mote.modules.createnote.domain.repository.NoteImageComponentRepository

class NoteImageComponentRepositoryImpl(
    private val noteImageDao: NoteImageDao,
    private val noteImageComponentDTOMapper: NoteImageComponentDTOMapper
) : NoteImageComponentRepository {

    override suspend fun insertNoteImageComponent(noteId: Int, order: Int): Long {
        val noteImageEntity = noteImageComponentDTOMapper.mapToNew(noteId = noteId, order = order)
        return noteImageDao.insertNoteImage(noteImageEntity)
    }

    override suspend fun deleteNoteImageComponent(componentId: Int) {
        noteImageDao.deleteNoteImage(componentId)
    }

    override suspend fun updateNoteImageUri(componentId: Int, newUri: String?) {
        noteImageDao.updateNoteImageUri(componentId = componentId, newUri = newUri)
    }

    override suspend fun getImageHighestOrder(noteId: Int): Int? {
        return noteImageDao.getNoteImagesHighestOrder(noteId)
    }

    override suspend fun updateNoteImageOrder(componentId: Int, newOrder: Int) {
        noteImageDao.updateNoteImageOrder(componentId = componentId, newOrder = newOrder)
    }
}
