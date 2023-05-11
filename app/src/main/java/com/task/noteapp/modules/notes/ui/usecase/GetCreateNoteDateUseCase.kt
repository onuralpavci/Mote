package com.task.noteapp.modules.notes.ui.usecase

import com.task.noteapp.modules.notes.ui.model.BaseNoteDate
import com.task.noteapp.utils.DATE_FORMAT
import com.task.noteapp.utils.TIME_FORMAT
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class GetCreateNoteDateUseCase @Inject constructor() {
    operator fun invoke(timeStamp: Long): BaseNoteDate.CreateDate {
        val now = Calendar.getInstance()
        val elapsedDays = TimeUnit.MILLISECONDS.toDays(now.timeInMillis - timeStamp)

        return when {
            elapsedDays < 1 -> {
                val format = SimpleDateFormat(TIME_FORMAT, Locale.getDefault())
                val formattedTime = format.format(Date(timeStamp))
                BaseNoteDate.CreateDate.Time(formattedTime)
            }
            elapsedDays == 1L -> BaseNoteDate.CreateDate.Yesterday
            elapsedDays in 2 until 7 -> BaseNoteDate.CreateDate.LastWeek
            else -> {
                val format = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
                val formattedDate = format.format(Date(timeStamp))
                BaseNoteDate.CreateDate.Time(formattedDate)
            }
        }
    }
}
