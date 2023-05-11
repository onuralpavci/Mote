package com.avci.mote.modules.notes.ui.model

import com.avci.mote.modules.customview.customscreenstate.ui.model.ScreenStateConfiguration

data class NotesPreview(
    val screenStateConfiguration: ScreenStateConfiguration?,
    val noteList: List<BaseNoteCardListItem>,
    val isEmptyStateVisible: Boolean
)
