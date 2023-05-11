package com.task.noteapp.modules.core.ui.viewholder

import androidx.recyclerview.widget.DiffUtil
import com.task.noteapp.modules.core.ui.model.RecyclerListItem

class BaseDiffUtil<T : RecyclerListItem> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem areItemsTheSame newItem
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem areContentsTheSame newItem
    }
}
