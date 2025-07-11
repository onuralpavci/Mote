package com.avci.mote.modules.notes.data.repository

import com.avci.mote.database.dao.NoteDao
import com.avci.mote.database.dao.NoteHeadingDao
import com.avci.mote.database.dao.NoteImageDao
import com.avci.mote.database.dao.NoteTextAreaDao
import com.avci.mote.modules.createnote.data.mapper.NoteDTOMapper
import com.avci.mote.modules.createnote.data.mapper.NoteHeadingComponentDTOMapper
import com.avci.mote.modules.createnote.data.mapper.NoteImageComponentDTOMapper
import com.avci.mote.modules.createnote.data.mapper.NoteTextAreaComponentDTOMapper
import com.avci.mote.modules.createnote.domain.model.BaseNoteComponentDTO
import com.avci.mote.modules.createnote.domain.model.NoteDTO
import com.avci.mote.modules.notes.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

class NotesRepositoryImpl(
    private val noteDao: NoteDao,
    private val noteTextAreaDao: NoteTextAreaDao,
    private val noteHeadingDao: NoteHeadingDao,
    private val noteImageDao: NoteImageDao,
    private val noteDTOMapper: NoteDTOMapper,
    private val noteTextAreaComponentDTOMapper: NoteTextAreaComponentDTOMapper,
    private val noteHeadingComponentDTOMapper: NoteHeadingComponentDTOMapper,
    private val noteImageComponentDTOMapper: NoteImageComponentDTOMapper
) : NotesRepository {
    override suspend fun getNotesDTOFlow(): Flow<List<NoteDTO>> {
        return noteDao.getAllAsFlow().map { noteEntityList ->
            noteEntityList.map { noteEntity ->
                noteDTOMapper.mapTo(noteEntity)
            }
        }
    }

    override suspend fun getAllNoteComponentsDTOFlow(): Flow<List<BaseNoteComponentDTO>> {
        val textAreaFlow = noteTextAreaDao.getAllAsFlow()
        val headingFlow = noteHeadingDao.getAllAsFlow()
        val imageFlow = noteImageDao.getAllAsFlow()
        val combined = combine(textAreaFlow, headingFlow, imageFlow) { textAreas, headings, images ->
            mutableListOf<BaseNoteComponentDTO>().apply {
                val textAreaComponents = textAreas.map { textAreaEntity ->
                    noteTextAreaComponentDTOMapper.mapTo(textAreaEntity)
                }
                val headingComponents = headings.map { noteHeadingEntity ->
                    noteHeadingComponentDTOMapper.mapTo(noteHeadingEntity)
                }
                val imageComponents = images.map { imageEntity ->
                    noteImageComponentDTOMapper.mapTo(imageEntity)
                }
                addAll(textAreaComponents)
                addAll(headingComponents)
                addAll(imageComponents)
            }
        }
        return combined
    }

    override suspend fun getNoteDTO(noteId: Int): NoteDTO? {
        val noteEntity = noteDao.getNoteById(noteId = noteId) ?: return null
        return noteDTOMapper.mapTo(noteEntity)
    }

    override suspend fun getNoteDTOFlow(noteId: Int): Flow<NoteDTO?> {
        return noteDao.getNoteByIdAsFlow(noteId = noteId).map { noteEntity ->
            noteEntity?.let { noteDTOMapper.mapTo(it) }
        }
    }

    override suspend fun getNoteComponentsDTOFlow(noteId: Int): Flow<List<BaseNoteComponentDTO>> {
        val textAreaFlow = noteTextAreaDao.getNoteTextAreaByNoteIdAsFlow(noteId)
        val headingFlow = noteHeadingDao.getNoteHeadingByNoteIdAsFlow(noteId)
        val imageFlow = noteImageDao.getNoteImagesByNoteIdAsFlow(noteId)
        val combined = combine(textAreaFlow, headingFlow, imageFlow) { textAreas, headings, images ->
            mutableListOf<BaseNoteComponentDTO>().apply {
                val textAreaComponents = textAreas.map { textAreaEntity ->
                    noteTextAreaComponentDTOMapper.mapTo(textAreaEntity)
                }
                val headingComponents = headings.map { headingEntity ->
                    noteHeadingComponentDTOMapper.mapTo(headingEntity)
                }
                val imageComponents = images.map { imageEntity ->
                    noteImageComponentDTOMapper.mapTo(imageEntity)
                }
                addAll(textAreaComponents)
                addAll(headingComponents)
                addAll(imageComponents)
            }
        }
        return combined
    }

    override suspend fun insertNote(createTimeStamp: Long): Int {
        val noteEntity = noteDTOMapper.mapToNew(createTimeStamp)
        return noteDao.insertNote(noteEntity).toInt()
    }

    override suspend fun deleteNote(nodeId: Int) {
        noteDao.deleteNote(nodeId)
    }

    override suspend fun updateNoteTitle(noteId: Int, newTitle: String?) {
        noteDao.updateNoteTitle(noteId = noteId, newTitle = newTitle)
    }

    override suspend fun updateNoteLastEditTimeStamp(noteId: Int, lastEditTimeStamp: Long) {
        noteDao.updateNoteLastEditTimeStamp(noteId = noteId, lastEditTimeStamp = lastEditTimeStamp)
    }

    override suspend fun updateNoteIsSaved(noteId: Int, isSaved: Boolean) {
        noteDao.updateNoteIsSaved(noteId = noteId, isSaved = isSaved)
    }
}
