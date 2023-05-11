package com.task.noteapp.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "note_entity"
)
data class NoteEntity(
    @ColumnInfo(name = "title")
    val title: String?,

    @ColumnInfo(name = "create_time_stamp")
    val createTimeStamp: Long,

    @ColumnInfo(name = "update_time_stamp")
    val updateTimeStamp: Long?,

    @ColumnInfo(name = "is_saved")
    val isSaved: Boolean,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID_COLUMN_NAME)
    var id: Int = 0
) {
    companion object {
        const val ID_COLUMN_NAME = "id"
    }
}
