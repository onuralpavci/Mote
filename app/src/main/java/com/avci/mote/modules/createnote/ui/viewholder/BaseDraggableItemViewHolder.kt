package com.avci.mote.modules.createnote.ui.viewholder

import android.view.View
import android.widget.Button
import com.avci.mote.modules.core.ui.viewholder.BaseViewHolder

// TODO: Create BaseDraggableItemViewHolder
abstract class BaseDraggableItemViewHolder<T>(
    itemView: View,
    private val listener: BaseDraggableItemListener<T>
) : BaseViewHolder<T>(itemView) {

    abstract val dragButton: Button

    fun setDragButtonOnTouchClickListener() {

    }

    interface BaseDraggableItemListener<T> {
        fun onDragButtonPressed(viewHolder: BaseDraggableItemViewHolder<T>)
    }
}
