package com.avci.mote.modules.createnote.ui.model

import com.avci.mote.modules.notes.ui.model.BaseNoteDate
import com.avci.mote.utils.model.Event

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
