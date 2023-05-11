package com.avci.mote.modules.notes.ui.usecase

import com.avci.mote.modules.notes.ui.model.BaseNoteDate
import com.avci.mote.utils.DATE_FORMAT
import com.avci.mote.utils.TIME_FORMAT
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class GetUpdateNoteDateUseCase @Inject constructor() {
    operator fun invoke(timeStamp: Long): BaseNoteDate.UpdateDate {
        val now = Calendar.getInstance()
        val elapsedDays = TimeUnit.MILLISECONDS.toDays(now.timeInMillis - timeStamp)

        return when {
            elapsedDays < 1 -> {
                val format = SimpleDateFormat(TIME_FORMAT, Locale.getDefault())
                val formattedTime = format.format(Date(timeStamp))
                BaseNoteDate.UpdateDate.Time(formattedTime)
            }
            elapsedDays == 1L -> BaseNoteDate.UpdateDate.Yesterday
            elapsedDays in 2 until 7 -> BaseNoteDate.UpdateDate.LastWeek
            else -> {
                val format = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
                val formattedDate = format.format(Date(timeStamp))
                BaseNoteDate.UpdateDate.Time(formattedDate)
            }
        }
    }
}
