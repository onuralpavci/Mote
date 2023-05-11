package com.avci.mote.modules.savednotes.ui.model

import com.avci.mote.modules.customview.customscreenstate.ui.model.ScreenStateConfiguration
import com.avci.mote.modules.notes.ui.model.BaseNoteCardListItem

data class SavedNotesPreview(
    val screenStateConfiguration: ScreenStateConfiguration?,
    val noteList: List<BaseNoteCardListItem.NoteCardListItem>,
    val isEmptyStateVisible: Boolean
)
