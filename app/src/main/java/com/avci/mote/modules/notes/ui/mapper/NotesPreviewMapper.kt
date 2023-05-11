package com.avci.mote.modules.notes.ui.mapper

import com.avci.mote.modules.customview.customscreenstate.ui.model.ScreenStateConfiguration
import com.avci.mote.modules.notes.ui.model.BaseNoteCardListItem
import com.avci.mote.modules.notes.ui.model.NotesPreview
import javax.inject.Inject

class NotesPreviewMapper @Inject constructor() {
    fun mapTo(
        screenStateConfiguration: ScreenStateConfiguration?,
        noteList: List<BaseNoteCardListItem>,
        isEmptyStateVisible: Boolean
    ): NotesPreview {
        return NotesPreview(
            screenStateConfiguration = screenStateConfiguration,
            noteList = noteList,
            isEmptyStateVisible = isEmptyStateVisible
        )
    }

    fun mapToInitialPreview(): NotesPreview {
        return NotesPreview(
            screenStateConfiguration = null,
            noteList = emptyList(),
            isEmptyStateVisible = false
        )
    }
}
