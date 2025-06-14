package com.avci.mote.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.avci.mote.database.entity.NoteHeadingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteHeadingDao {
    @Query("SELECT * FROM note_heading_entity")
    suspend fun getAll(): List<NoteHeadingEntity>

    @Query("SELECT * FROM note_heading_entity WHERE note_id = :noteId")
    suspend fun getNoteHeadingByNoteId(noteId: Int): List<NoteHeadingEntity>

    @Query("SELECT * FROM note_heading_entity WHERE note_id = :noteId")
    fun getNoteHeadingByNoteIdAsFlow(noteId: Int): Flow<List<NoteHeadingEntity>>

    @Query("SELECT * FROM note_heading_entity")
    fun getAllAsFlow(): Flow<List<NoteHeadingEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNoteHeading(note: NoteHeadingEntity): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNoteHeading(note: NoteHeadingEntity)

    @Query("DELETE FROM note_heading_entity WHERE id = :noteHeadingId")
    suspend fun deleteNoteHeading(noteHeadingId: Int)

    @Query("DELETE FROM note_heading_entity")
    suspend fun deleteAllNoteHeadings()

    @Query("SELECT MAX(`order`) FROM note_heading_entity WHERE note_id = :noteId")
    suspend fun getHeadingHighestOrder(noteId: Int): Int?

    @Query("UPDATE note_heading_entity SET text = :newText WHERE id = :componentId")
    suspend fun updateHeadingText(componentId: Int, newText: String?)

    @Query("UPDATE note_heading_entity SET `order` = :newOrder WHERE id = :componentId")
    suspend fun updateHeadingOrder(componentId: Int, newOrder: Int)
}
