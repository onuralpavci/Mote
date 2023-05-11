package com.avci.mote.utils

import android.content.Context
import com.avci.mote.R
import com.avci.mote.modules.notes.ui.model.BaseNoteDate

const val TIME_FORMAT = "HH:mm"
const val DATE_FORMAT = "MMM dd"
fun Context.getFormattedTime(date: BaseNoteDate): String {
    val prefix = getString(date.prefixResId)
    val dateTime = when (date) {
        is BaseNoteDate.CreateDate -> {
            when (date) {
                is BaseNoteDate.CreateDate.Time -> date.time
                is BaseNoteDate.CreateDate.Yesterday -> getString(R.string.yestarday)
                is BaseNoteDate.CreateDate.LastWeek -> getString(R.string.last_week)
                is BaseNoteDate.CreateDate.Date -> date.date
            }
        }
        is BaseNoteDate.UpdateDate -> {
            when (date) {
                is BaseNoteDate.UpdateDate.Time -> date.time
                is BaseNoteDate.UpdateDate.Yesterday -> getString(R.string.yestarday)
                is BaseNoteDate.UpdateDate.LastWeek -> getString(R.string.last_week)
                is BaseNoteDate.UpdateDate.Date -> date.date
            }

        }
    }
    return "$prefix $dateTime"
}
