package com.task.noteapp.modules.createnote.data.mapper

import com.task.noteapp.database.entity.NoteTextAreaEntity
import com.task.noteapp.modules.createnote.domain.model.BaseNoteComponentDTO.TextAreaComponentDTO
import javax.inject.Inject

class NoteTextAreaComponentDTOMapper @Inject constructor() {
    fun mapTo(noteTextAreaEntity: NoteTextAreaEntity): TextAreaComponentDTO {
        return TextAreaComponentDTO(
            id = noteTextAreaEntity.id,
            order = noteTextAreaEntity.order,
            text = noteTextAreaEntity.text,
            noteId = noteTextAreaEntity.noteId,
        )
    }

    fun mapTo(textAreaComponentDTO: TextAreaComponentDTO): NoteTextAreaEntity {
        return NoteTextAreaEntity(
            id = textAreaComponentDTO.id,
            order = textAreaComponentDTO.order,
            text = textAreaComponentDTO.text,
            noteId = textAreaComponentDTO.noteId,
        )
    }

    fun mapToNew(noteId: Int, order: Int): NoteTextAreaEntity {
        return NoteTextAreaEntity(
            order = order,
            text = null,
            noteId = noteId,
        )
    }
}
