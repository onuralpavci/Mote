package com.avci.mote.modules.customview.delegation.draggableitemviewdelegation

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.widget.Button

class DraggableItemViewDelegate : DraggableItemViewComponent {

    override lateinit var dragButton: Button

    override fun initDraggableItemViewComponent(dragButton: Button) {
        this.dragButton = dragButton
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun initDraggableItemViewController(onDragged: () -> Unit) {
        dragButton.setOnTouchListener { _, event ->
            if (event.actionMasked == MotionEvent.ACTION_MOVE || event.actionMasked == MotionEvent.ACTION_DOWN) {
                onDragged.invoke()
            }
            false
        }
    }
}
