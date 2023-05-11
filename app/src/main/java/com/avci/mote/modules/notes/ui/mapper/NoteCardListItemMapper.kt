package com.avci.mote.modules.notes.ui.mapper

import com.avci.mote.modules.notes.ui.model.BaseNoteCardListItem
import com.avci.mote.modules.notes.ui.model.BaseNoteDate
import javax.inject.Inject

class NoteCardListItemMapper @Inject constructor() {
    fun mapTo(
        noteId: Int,
        title: String?,
        imageUri: String?,
        content: String?,
        emptyTitleResId: Int,
        emptyContentResId: Int,
        createUpdateDate: BaseNoteDate
    ): BaseNoteCardListItem.NoteCardListItem {
        return BaseNoteCardListItem.NoteCardListItem(
            noteId = noteId,
            title = title,
            imageUri = imageUri,
            content = content,
            emptyTitleResId = emptyTitleResId,
            emptyContentResId = emptyContentResId,
            createUpdateDate = createUpdateDate
        )
    }

    fun mapToAddNoteCardItem() = BaseNoteCardListItem.AddNoteCardListItem
}
