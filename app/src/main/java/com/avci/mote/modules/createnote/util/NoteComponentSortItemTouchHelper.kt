package com.avci.mote.modules.createnote.util

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.DOWN
import androidx.recyclerview.widget.ItemTouchHelper.UP
import androidx.recyclerview.widget.RecyclerView
import com.avci.mote.modules.createnote.ui.viewholder.CreateNoteImageItemViewHolder
import com.avci.mote.modules.createnote.ui.viewholder.CreateNoteTextAreaItemViewHolder

class NoteComponentSortItemTouchHelper(
    private val listener: ItemMoveListener
) : ItemTouchHelper.SimpleCallback(UP or DOWN, 0) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        if (!isViewHoldersInTheSameAdapter(viewHolder, target)) return false
        if (target !is CreateNoteTextAreaItemViewHolder && target !is CreateNoteImageItemViewHolder) return false

        val fromPosition = viewHolder.bindingAdapterPosition
        val toPosition = target.bindingAdapterPosition
        listener.onItemMove(fromPosition, toPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        // Nothing to do here
    }

    override fun isLongPressDragEnabled(): Boolean {
        return false
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        if (
            viewHolder is CreateNoteTextAreaItemViewHolder ||
            viewHolder is CreateNoteImageItemViewHolder
        ) {
            listener.onItemReleased()
        }
    }

    private fun isViewHoldersInTheSameAdapter(
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return viewHolder.bindingAdapter == target.bindingAdapter
    }

    interface ItemMoveListener {
        fun onItemMove(fromPosition: Int, toPosition: Int)
        fun onItemReleased()
    }
}
