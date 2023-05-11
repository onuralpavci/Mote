package com.avci.mote.modules.searchnotes.ui.model

import com.avci.mote.modules.customview.customscreenstate.ui.model.ScreenStateConfiguration
import com.avci.mote.modules.notes.ui.model.BaseNoteCardListItem.NoteCardListItem

data class SearchNotesPreview(
    val screenStateConfiguration: ScreenStateConfiguration?,
    val noteList: List<NoteCardListItem>,
    val isEmptyStateVisible: Boolean,
    val query: String?
)
