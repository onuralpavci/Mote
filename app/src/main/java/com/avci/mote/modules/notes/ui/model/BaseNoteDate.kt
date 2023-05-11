package com.avci.mote.modules.notes.ui.model

import com.avci.mote.R

sealed class BaseNoteDate {
    abstract val prefixResId: Int

    sealed class CreateDate : BaseNoteDate() {
        data class Time(
            val time: String,
            override val prefixResId: Int = R.string.created_at
        ) : CreateDate()

        object Yesterday : CreateDate() {
            override val prefixResId: Int = R.string.created
        }

        object LastWeek : CreateDate() {
            override val prefixResId: Int = R.string.created
        }

        data class Date(
            val date: String,
            override val prefixResId: Int = R.string.created_on
        ) : CreateDate()
    }

    sealed class UpdateDate : BaseNoteDate() {
        data class Time(
            val time: String,
            override val prefixResId: Int = R.string.last_edited_at
        ) : UpdateDate()

        object Yesterday : UpdateDate() {
            override val prefixResId: Int = R.string.last_edited
        }

        object LastWeek : UpdateDate() {
            override val prefixResId: Int = R.string.last_edited
        }

        data class Date(
            val date: String,
            override val prefixResId: Int = R.string.last_edited_on
        ) : UpdateDate()
    }
}
