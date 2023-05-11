package com.task.noteapp.modules.createnote.ui.mapper

import com.task.noteapp.modules.createnote.domain.model.BaseNoteComponent
import com.task.noteapp.modules.createnote.ui.model.BaseCreateNoteListItem
import javax.inject.Inject

class BaseNoteComponentMapper @Inject constructor() {
    fun mapTo(baseNoteComponent: BaseNoteComponent): BaseCreateNoteListItem.BaseNoteComponentItem {
        return when (baseNoteComponent) {
            is BaseNoteComponent.TextAreaComponent -> {
                BaseCreateNoteListItem.BaseNoteComponentItem.TextAreaItem(
                    text = baseNoteComponent.text.orEmpty(),
                    componentId = baseNoteComponent.id,
                    order = baseNoteComponent.order
                )
            }
            is BaseNoteComponent.ImageComponent -> {
                BaseCreateNoteListItem.BaseNoteComponentItem.ImageItem(
                    uri = baseNoteComponent.uri.orEmpty(),
                    componentId = baseNoteComponent.id,
                    order = baseNoteComponent.order
                )
            }
        }
    }
}
