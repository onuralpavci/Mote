package com.task.noteapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.task.noteapp.database.NoteDatabase.Companion.LATEST_DB_VERSION
import com.task.noteapp.database.dao.NoteDao
import com.task.noteapp.database.dao.NoteImageDao
import com.task.noteapp.database.dao.NoteTextAreaDao
import com.task.noteapp.database.entity.NoteEntity
import com.task.noteapp.database.entity.NoteImageEntity
import com.task.noteapp.database.entity.NoteTextAreaEntity

@Database(
    entities = [
        NoteEntity::class,
        NoteTextAreaEntity::class,
        NoteImageEntity::class
    ],
    version = LATEST_DB_VERSION,
    exportSchema = true
)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun noteTextAreaDao(): NoteTextAreaDao
    abstract fun noteImageDao(): NoteImageDao

    companion object {
        const val LATEST_DB_VERSION = 2
        const val DATABASE_NAME = "notes-db"

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE note_entity ADD COLUMN is_saved INTEGER NOT NULL DEFAULT 0")
            }
        }
    }
}
