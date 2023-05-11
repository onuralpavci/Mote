package com.task.noteapp.modules.savednotes.ui.model

import com.task.noteapp.modules.customview.customscreenstate.ui.model.ScreenStateConfiguration
import com.task.noteapp.modules.notes.ui.model.BaseNoteCardListItem

data class SavedNotesPreview(
    val screenStateConfiguration: ScreenStateConfiguration?,
    val noteList: List<BaseNoteCardListItem.NoteCardListItem>,
    val isEmptyStateVisible: Boolean
)
