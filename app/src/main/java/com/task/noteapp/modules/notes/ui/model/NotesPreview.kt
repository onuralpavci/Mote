package com.task.noteapp.modules.notes.ui.model

import com.task.noteapp.modules.customview.customscreenstate.ui.model.ScreenStateConfiguration

data class NotesPreview(
    val screenStateConfiguration: ScreenStateConfiguration?,
    val noteList: List<BaseNoteCardListItem>,
    val isEmptyStateVisible: Boolean
)
