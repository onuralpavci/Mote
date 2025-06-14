package com.avci.mote.modules.notes.ui.usecase

import com.avci.mote.R
import com.avci.mote.modules.createnote.domain.model.BaseNoteComponent.ImageComponent
import com.avci.mote.modules.createnote.domain.model.BaseNoteComponent.TextAreaComponent
import com.avci.mote.modules.notes.domain.usecase.GetNoteListFlowUseCase
import com.avci.mote.modules.notes.ui.mapper.NoteCardListItemMapper
import com.avci.mote.modules.notes.ui.model.BaseNoteCardListItem
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNoteCardListItemFlowUseCase @Inject constructor(
    private val getNoteListFlowUseCase: GetNoteListFlowUseCase,
    private val getCreateNoteDateUseCase: GetCreateNoteDateUseCase,
    private val getUpdateNoteDateUseCase: GetUpdateNoteDateUseCase,
    private val noteCardListItemMapper: NoteCardListItemMapper
) {
    suspend operator fun invoke(
        query: String? = null,
        filterSavedNotes: Boolean = false
    ): Flow<List<BaseNoteCardListItem>> {
        return getNoteListFlowUseCase.invoke(query = query, filterSavedNotes = filterSavedNotes).map { noteList ->
            noteList.map { note ->
                val noteComponents = note.noteComponents
                val imageComponent = noteComponents.filterIsInstance<ImageComponent>().firstOrNull {
                    it.uri?.isNotBlank() == true
                }
                // TODO: When base TextComponent class is created, replace TextAreaComponent with it
                val textAreaComponent = noteComponents.filterIsInstance<TextAreaComponent>().firstOrNull {
                    it.text?.isNotBlank() == true
                }
                val createDate = getCreateNoteDateUseCase.invoke(note.createTimeStamp)
                val updateDate = note.updateTimeStamp?.let { getUpdateNoteDateUseCase.invoke(it) }
                noteCardListItemMapper.mapTo(
                    noteId = note.id,
                    title = note.title,
                    imageUri = imageComponent?.uri,
                    content = textAreaComponent?.text,
                    emptyTitleResId = R.string.untitled_note,
                    emptyContentResId = R.string.empty_note,
                    createUpdateDate = updateDate ?: createDate
                )
            }
        }
    }
}
