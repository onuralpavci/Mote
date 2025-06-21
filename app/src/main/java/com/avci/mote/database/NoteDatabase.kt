package com.avci.mote.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.avci.mote.database.NoteDatabase.Companion.LATEST_DB_VERSION
import com.avci.mote.database.dao.NoteDao
import com.avci.mote.database.dao.NoteHeadingDao
import com.avci.mote.database.dao.NoteImageDao
import com.avci.mote.database.dao.NoteTextAreaDao
import com.avci.mote.database.entity.NoteEntity
import com.avci.mote.database.entity.NoteHeadingEntity
import com.avci.mote.database.entity.NoteImageEntity
import com.avci.mote.database.entity.NoteTextAreaEntity

@Database(
    entities = [
        NoteEntity::class,
        NoteTextAreaEntity::class,
        NoteHeadingEntity::class,
        NoteImageEntity::class
    ],
    version = LATEST_DB_VERSION,
    exportSchema = true
)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun noteTextAreaDao(): NoteTextAreaDao
    abstract fun noteImageDao(): NoteImageDao
    abstract fun noteHeadingDao(): NoteHeadingDao

    companion object {
        const val LATEST_DB_VERSION = 3
        const val DATABASE_NAME = "notes-db"

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE note_entity ADD COLUMN is_saved INTEGER NOT NULL DEFAULT 0")
            }
        }
        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS note_heading_entity (
                        note_id INTEGER NOT NULL,
                        text TEXT,
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        `order` INTEGER NOT NULL,
                        FOREIGN KEY(note_id) REFERENCES note_entity(id) ON DELETE CASCADE
                    )
                    """.trimIndent()
                )
            }
        }
    }
}
