package com.avci.mote.modules.customview.delegation.draggableitemviewholderdelegation

import com.avci.mote.modules.customview.delegation.draggableitemviewdelegation.DraggableItemViewComponent

class DraggableItemViewHolderDelegate<T : DraggableItemViewComponent> : DraggableItemViewHolderComponent<T> {

    override lateinit var dragItemView: T

    override fun initDraggableItemViewHolderComponent(dragItemView: T, onDragged: () -> Unit) {
        this.dragItemView = dragItemView
        initDraggableItemViewHolderController(onDragged)
    }

    override fun initDraggableItemViewHolderController(onDragged: () -> Unit) {
        this.dragItemView.initDraggableItemViewController(onDragged)
    }
}
