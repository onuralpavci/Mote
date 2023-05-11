package com.task.noteapp.modules.createnote.ui.model

import com.task.noteapp.modules.notes.ui.model.BaseNoteDate
import com.task.noteapp.utils.model.Event

data class CreateNotePreview(
    val noteId: Int,
    val createNoteListItems: List<BaseCreateNoteListItem>,
    val createDate: BaseNoteDate.CreateDate,
    val updateDate: BaseNoteDate.UpdateDate?,
    val openChatGPTEvent: Event<Unit>?,
    val navBackEvent: Event<Unit>?,
    val isSaved: Boolean,
    val showSaveSuccessDialogEvent: Event<Unit>?
)
