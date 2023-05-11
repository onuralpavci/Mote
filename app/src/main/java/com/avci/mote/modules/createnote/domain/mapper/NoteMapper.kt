package com.avci.mote.modules.createnote.domain.mapper

import com.avci.mote.modules.createnote.domain.model.BaseNoteComponentDTO
import com.avci.mote.modules.createnote.domain.model.Note
import com.avci.mote.modules.createnote.domain.model.NoteDTO
import javax.inject.Inject

class NoteMapper @Inject constructor(
    private val noteComponentMapper: NoteComponentMapper
) {
    fun mapTo(note: Note): NoteDTO {
        return NoteDTO(
            id = note.id,
            title = note.title,
            createTimeStamp = note.createTimeStamp,
            updateTimeStamp = note.updateTimeStamp,
            isSaved = note.isSaved
        )
    }

    fun mapTo(noteDTO: NoteDTO, noteComponentDTOList: List<BaseNoteComponentDTO>): Note {
        val noteComponents = noteComponentDTOList.map { componentDTO ->
            when (componentDTO) {
                is BaseNoteComponentDTO.TextAreaComponentDTO -> noteComponentMapper.mapTo(componentDTO)
                is BaseNoteComponentDTO.ImageComponentDTO -> noteComponentMapper.mapTo(componentDTO)
            }
        }
        return Note(
            id = noteDTO.id,
            title = noteDTO.title,
            createTimeStamp = noteDTO.createTimeStamp,
            updateTimeStamp = noteDTO.updateTimeStamp,
            noteComponents = noteComponents,
            isSaved = noteDTO.isSaved
        )
    }
}
