package com.task.noteapp.modules.core.ui.model

interface RecyclerListItem {
    infix fun areItemsTheSame(other: RecyclerListItem): Boolean
    infix fun areContentsTheSame(other: RecyclerListItem): Boolean
}
