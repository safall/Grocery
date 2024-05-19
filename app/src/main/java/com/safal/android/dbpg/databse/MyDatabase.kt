package com.safal.android.dbpg.databse

import android.util.Log
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.safal.android.dbpg.databse.dao.TaskDao
import com.safal.android.dbpg.databse.dao.TaskOwnerDao
import com.safal.android.dbpg.databse.entity.TaskEntity
import com.safal.android.dbpg.databse.entity.TaskOwnerEntity

@Database(
    entities = [
        TaskEntity::class,
        TaskOwnerEntity::class
    ], version = 5, exportSchema = true
)
abstract class MyDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    abstract fun taskOwnerDao(): TaskOwnerDao


    companion object {
        val DB_CALLBACK = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                db.execSQL(CREATE_DELETE_TASK_OWNER_TRIGGER)
            }
        }

        val migration1to2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("CREATE TABLE IF NOT EXISTS taskOwner (id INTEGER PRIMARY KEY NOT NULL, name TEXT NOT NULL)")
            }
        }

        const val CREATE_DELETE_TASK_OWNER_TRIGGER =
            """
            CREATE TRIGGER IF NOT EXISTS deleteTaskWhenDeletingTaskOwnerTrigger
                AFTER DELETE ON taskOwner
            BEGIN
                DELETE FROM task WHERE id = OLD.id;
            END
            """

        val migration2to3 = object : Migration(2, 3) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(CREATE_DELETE_TASK_OWNER_TRIGGER)
            }
        }

        val migration3to4 = object : Migration(3, 4) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE task ADD COLUMN priority TEXT NOT NULL DEFAULT 'LOW'")
                db.execSQL("ALTER TABLE taskOwner ADD COLUMN address TEXT NOT NULL DEFAULT 'Kathmandu'")
            }
        }


        val migration4to5 = object : Migration(4, 5) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE taskOwner RENAME TO temp_taskOwner")
                db.execSQL("CREATE TABLE IF NOT EXISTS taskOwner (id INTEGER PRIMARY KEY NOT NULL, name TEXT NOT NULL)")
                db.execSQL("INSERT INTO taskOwner (id, name) SELECT id, name FROM temp_taskOwner")
                db.execSQL("DROP TABLE temp_taskOwner")

                // Alternative that works too
//                db.execSQL("CREATE TABLE IF NOT EXISTS temp_taskOwner (id INTEGER PRIMARY KEY NOT NULL, name TEXT NOT NULL)")
//                db.execSQL("INSERT INTO temp_taskOwner (id, name) SELECT id, name FROM taskOwner")
//                db.execSQL("DROP TABLE taskOwner")
//                db.execSQL("ALTER TABLE temp_taskOwner RENAME TO taskOwner")
            }
        }
    }
}
