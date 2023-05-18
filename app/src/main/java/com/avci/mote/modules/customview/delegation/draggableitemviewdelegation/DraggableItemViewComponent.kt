package com.avci.mote.modules.customview.delegation.draggableitemviewdelegation

import android.widget.Button

interface DraggableItemViewComponent : DraggableItemViewController {
    var dragButton: Button
    fun initDraggableItemViewComponent(dragButton: Button)
}
