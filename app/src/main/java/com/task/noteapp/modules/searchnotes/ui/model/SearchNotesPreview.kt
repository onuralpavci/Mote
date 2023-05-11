package com.task.noteapp.modules.searchnotes.ui.model

import com.task.noteapp.modules.customview.customscreenstate.ui.model.ScreenStateConfiguration
import com.task.noteapp.modules.notes.ui.model.BaseNoteCardListItem.NoteCardListItem

data class SearchNotesPreview(
    val screenStateConfiguration: ScreenStateConfiguration?,
    val noteList: List<NoteCardListItem>,
    val isEmptyStateVisible: Boolean,
    val query: String?
)
