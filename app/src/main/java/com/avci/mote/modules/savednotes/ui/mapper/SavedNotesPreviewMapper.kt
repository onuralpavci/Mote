package com.avci.mote.modules.savednotes.ui.mapper

import com.avci.mote.modules.customview.customscreenstate.ui.model.ScreenStateConfiguration
import com.avci.mote.modules.notes.ui.mapper.ScreenStateConfigurationMapper
import com.avci.mote.modules.notes.ui.model.BaseNoteCardListItem
import com.avci.mote.modules.savednotes.ui.model.SavedNotesPreview
import javax.inject.Inject

class SavedNotesPreviewMapper @Inject constructor(
    private val screenStateConfigurationMapper: ScreenStateConfigurationMapper,
) {
    fun mapTo(
        screenStateConfiguration: ScreenStateConfiguration?,
        noteList: List<BaseNoteCardListItem.NoteCardListItem>,
        isEmptyStateVisible: Boolean
    ): SavedNotesPreview {
        return SavedNotesPreview(
            screenStateConfiguration = screenStateConfiguration,
            noteList = noteList,
            isEmptyStateVisible = isEmptyStateVisible
        )
    }

    fun mapToInitialPreview(): SavedNotesPreview {
        return SavedNotesPreview(
            screenStateConfiguration = screenStateConfigurationMapper.mapToNoSavedNoteState(),
            noteList = emptyList(),
            isEmptyStateVisible = true
        )
    }
}
