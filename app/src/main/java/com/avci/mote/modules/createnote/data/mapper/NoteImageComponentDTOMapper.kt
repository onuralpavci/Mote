package com.avci.mote.modules.createnote.data.mapper

import com.avci.mote.database.entity.NoteImageEntity
import com.avci.mote.modules.createnote.domain.model.BaseNoteComponentDTO.ImageComponentDTO
import javax.inject.Inject

class NoteImageComponentDTOMapper @Inject constructor() {
    fun mapTo(noteImageEntity: NoteImageEntity): ImageComponentDTO {
        return ImageComponentDTO(
            id = noteImageEntity.id,
            order = noteImageEntity.order,
            uri = noteImageEntity.uri,
            noteId = noteImageEntity.noteId,
        )
    }

    fun mapTo(noteImageComponentDTO: ImageComponentDTO): NoteImageEntity {
        return NoteImageEntity(
            id = noteImageComponentDTO.id,
            order = noteImageComponentDTO.order,
            uri = noteImageComponentDTO.uri,
            noteId = noteImageComponentDTO.noteId,
        )
    }

    fun mapToNew(noteId: Int, order: Int): NoteImageEntity {
        return NoteImageEntity(
            order = order,
            uri = null,
            noteId = noteId,
        )
    }
}
