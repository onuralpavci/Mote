package com.avci.mote.modules.createnote.ui.mapper

import com.avci.mote.modules.createnote.domain.model.BaseNoteComponent
import com.avci.mote.modules.createnote.ui.model.BaseCreateNoteListItem
import javax.inject.Inject

class BaseNoteComponentMapper @Inject constructor() {
    fun mapTo(baseNoteComponent: BaseNoteComponent): BaseCreateNoteListItem.BaseNoteComponentItem {
        return when (baseNoteComponent) {
            is BaseNoteComponent.TextAreaComponent -> {
                BaseCreateNoteListItem.BaseNoteComponentItem.TextAreaItem(
                    text = baseNoteComponent.text.orEmpty(),
                    componentId = baseNoteComponent.id
                )
            }
            is BaseNoteComponent.ImageComponent -> {
                BaseCreateNoteListItem.BaseNoteComponentItem.ImageItem(
                    uri = baseNoteComponent.uri.orEmpty(),
                    componentId = baseNoteComponent.id
                )
            }
            is BaseNoteComponent.HeadingComponent -> {
                BaseCreateNoteListItem.BaseNoteComponentItem.HeadingItem(
                    text = baseNoteComponent.text.orEmpty(),
                    componentId = baseNoteComponent.id
                )
            }
        }
    }
}
