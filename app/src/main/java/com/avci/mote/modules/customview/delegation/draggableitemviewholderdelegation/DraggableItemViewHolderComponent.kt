package com.avci.mote.modules.customview.delegation.draggableitemviewholderdelegation

import com.avci.mote.modules.customview.delegation.draggableitemviewdelegation.DraggableItemViewComponent

interface DraggableItemViewHolderComponent<T : DraggableItemViewComponent> : DraggableItemViewHolderController {
    var dragItemView: T
    fun initDraggableItemViewHolderComponent(dragItemView: (T), onDragged: () -> Unit)
}
