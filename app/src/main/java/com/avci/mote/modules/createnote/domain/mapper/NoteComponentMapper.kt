package com.avci.mote.modules.createnote.domain.mapper

import com.avci.mote.modules.createnote.domain.model.BaseNoteComponent.HeadingComponent
import com.avci.mote.modules.createnote.domain.model.BaseNoteComponent.ImageComponent
import com.avci.mote.modules.createnote.domain.model.BaseNoteComponent.TextAreaComponent
import com.avci.mote.modules.createnote.domain.model.BaseNoteComponentDTO.HeadingComponentDTO
import com.avci.mote.modules.createnote.domain.model.BaseNoteComponentDTO.ImageComponentDTO
import com.avci.mote.modules.createnote.domain.model.BaseNoteComponentDTO.TextAreaComponentDTO
import javax.inject.Inject

class NoteComponentMapper @Inject constructor() {
    fun mapTo(textAreaComponent: TextAreaComponent): TextAreaComponentDTO {
        return TextAreaComponentDTO(
            id = textAreaComponent.id,
            order = textAreaComponent.order,
            noteId = textAreaComponent.noteId,
            text = textAreaComponent.text
        )
    }

    fun mapTo(textAreaComponentDTO: TextAreaComponentDTO): TextAreaComponent {
        return TextAreaComponent(
            id = textAreaComponentDTO.id,
            order = textAreaComponentDTO.order,
            noteId = textAreaComponentDTO.noteId,
            text = textAreaComponentDTO.text
        )
    }

    fun mapTo(headingComponentDTO: HeadingComponentDTO): HeadingComponent {
        return HeadingComponent(
            id = headingComponentDTO.id,
            order = headingComponentDTO.order,
            noteId = headingComponentDTO.noteId,
            text = headingComponentDTO.text
        )
    }

    fun mapTo(textAreaComponentDTO: ImageComponentDTO): ImageComponent {
        return ImageComponent(
            id = textAreaComponentDTO.id,
            order = textAreaComponentDTO.order,
            noteId = textAreaComponentDTO.noteId,
            uri = textAreaComponentDTO.uri
        )
    }
}
