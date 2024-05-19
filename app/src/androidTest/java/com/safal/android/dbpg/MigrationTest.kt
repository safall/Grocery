package com.safal.android.dbpg

import androidx.room.Room
import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.safal.android.dbpg.databse.MyDatabase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

private const val DB_NAME = "testdb"

@RunWith(AndroidJUnit4::class)
class MigrationTest {

    @get:Rule
    val helper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        MyDatabase::class.java.canonicalName,
        FrameworkSQLiteOpenHelperFactory()
    )

    private lateinit var db: SupportSQLiteDatabase

    @Before
    fun setup() {
        db = helper.createDatabase(DB_NAME, 1)
    }

    @After
    fun teardown() {
        db.close()
    }


    @Test
    fun verifyMigration1to2CreatedTaskOwnerTable() {
        db.execSQL("INSERT INTO task VALUES (1, 'test', 'test desc')")
        db.close()

        db = helper.runMigrationsAndValidate(
            DB_NAME,
            2,
            true,
            MyDatabase.migration1to2
        )

        db.query("SELECT name FROM sqlite_master WHERE type='table' AND name='taskOwner'").apply {
            assert(moveToFirst())
            assert(getString(getColumnIndex("name")).equals("taskOwner"))
        }
    }


    @Test
    fun testAllMigrations() {
        helper.createDatabase(DB_NAME, 1).apply { close() }

        Room.databaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            MyDatabase::class.java,
            DB_NAME
        )
            .addMigrations(MyDatabase.migration1to2)
            .build()
            .apply { close() }
    }
}
