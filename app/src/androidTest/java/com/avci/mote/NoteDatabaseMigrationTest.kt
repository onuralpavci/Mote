package com.avci.mote

import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.avci.mote.database.NoteDatabase
import java.io.IOException
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NoteDatabaseMigrationTest {

    @Rule
    @JvmField
    val helper: MigrationTestHelper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        NoteDatabase::class.java.canonicalName,
        FrameworkSQLiteOpenHelperFactory()
    )

    @Test
    @Throws(IOException::class)
    fun migrate1To2() {
        // Create the database with version 1
        var db = helper.createDatabase(TEST_DB, 1)

        // Insert some data
        db.execSQL("INSERT INTO note_entity (title, create_time_stamp, update_time_stamp) VALUES ('Test', 123456789, 123456789)")

        db.close()

        // Re-open the database with version 2 and provide the migration process.
        db = helper.runMigrationsAndValidate(TEST_DB, 2, true, NoteDatabase.MIGRATION_1_2)

        // Validate that the migrated data is correct and the new field is properly initialized.
        val cursor = db.query("SELECT * FROM note_entity WHERE title='Test'")
        cursor.moveToFirst()

        Assert.assertEquals("Test", cursor.getString(cursor.getColumnIndex("title")))
        Assert.assertEquals(123456789L, cursor.getLong(cursor.getColumnIndex("create_time_stamp")))
        Assert.assertEquals(123456789L, cursor.getLong(cursor.getColumnIndex("update_time_stamp")))
        Assert.assertEquals(0, cursor.getInt(cursor.getColumnIndex("is_saved")))

        cursor.close()
    }

    companion object {
        private const val TEST_DB = "migration-test"
    }
}
