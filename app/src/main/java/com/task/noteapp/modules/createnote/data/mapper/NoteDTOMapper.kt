package com.task.noteapp.modules.createnote.data.mapper

import com.task.noteapp.database.entity.NoteEntity
import com.task.noteapp.modules.createnote.domain.model.NoteDTO
import javax.inject.Inject

class NoteDTOMapper @Inject constructor() {
    fun mapTo(noteEntity: NoteEntity): NoteDTO {
        return NoteDTO(
            id = noteEntity.id,
            title = noteEntity.title,
            createTimeStamp = noteEntity.createTimeStamp,
            updateTimeStamp = noteEntity.updateTimeStamp,
            isSaved = noteEntity.isSaved
        )
    }

    fun mapTo(noteDTO: NoteDTO): NoteEntity {
        return NoteEntity(
            id = noteDTO.id,
            title = noteDTO.title,
            createTimeStamp = noteDTO.createTimeStamp,
            updateTimeStamp = noteDTO.updateTimeStamp,
            isSaved = noteDTO.isSaved
        )
    }

    fun mapToNew(createTimeStamp: Long): NoteEntity {
        return NoteEntity(
            createTimeStamp = createTimeStamp,
            updateTimeStamp = null,
            title = null,
            isSaved = false
        )
    }
}
