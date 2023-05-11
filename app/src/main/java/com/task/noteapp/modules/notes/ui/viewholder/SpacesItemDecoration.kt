package com.task.noteapp.modules.notes.ui.viewholder

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpacesItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        with(outRect) {
            val position = parent.getChildAdapterPosition(view)
            val isLeftItem = (position % 2) == 0
            if (isLeftItem) {
                right = space / 2
            } else {
                left = space / 2
            }
        }
    }
}
