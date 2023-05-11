package com.avci.mote.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.avci.mote.database.entity.NoteEntity.Companion.ID_COLUMN_NAME
import com.avci.mote.database.entity.NoteTextAreaEntity.Companion.NOTE_ID_COLUMN_NAME

@Entity(
    tableName = "note_image_entity",
    foreignKeys = [ForeignKey(
        entity = NoteEntity::class,
        parentColumns = [ID_COLUMN_NAME],
        childColumns = [NOTE_ID_COLUMN_NAME],
        onDelete = ForeignKey.CASCADE
    )]
)
data class NoteImageEntity(
    @ColumnInfo(name = "uri")
    val uri: String?,

    @ColumnInfo(name = "order")
    val order: Int,

    @ColumnInfo(name = NOTE_ID_COLUMN_NAME)
    var noteId: Int,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
) {
    companion object {
        const val NOTE_ID_COLUMN_NAME = "note_id"
    }
}
