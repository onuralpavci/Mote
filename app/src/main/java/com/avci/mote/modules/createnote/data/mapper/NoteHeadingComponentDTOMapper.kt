package com.avci.mote.modules.createnote.data.mapper

import com.avci.mote.database.entity.NoteHeadingEntity
import com.avci.mote.modules.createnote.domain.model.BaseNoteComponentDTO.HeadingComponentDTO
import javax.inject.Inject

class NoteHeadingComponentDTOMapper @Inject constructor() {
    fun mapTo(noteHeadingEntity: NoteHeadingEntity): HeadingComponentDTO {
        return HeadingComponentDTO(
            id = noteHeadingEntity.id,
            order = noteHeadingEntity.order,
            text = noteHeadingEntity.text,
            noteId = noteHeadingEntity.noteId,
        )
    }

    fun mapTo(headingComponentDTO: HeadingComponentDTO): NoteHeadingEntity {
        return NoteHeadingEntity(
            id = headingComponentDTO.id,
            order = headingComponentDTO.order,
            text = headingComponentDTO.text,
            noteId = headingComponentDTO.noteId,
        )
    }

    fun mapToNew(noteId: Int, order: Int): NoteHeadingEntity {
        return NoteHeadingEntity(
            order = order,
            text = null,
            noteId = noteId,
        )
    }
}
