package edu.miracosta.cs134.kwebb.todo2day.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    // Task 1: make constants for all database values
    // db name, version, table name, field names, primary key
    public static final String DATABASE_NAME = "ToDo2Day";
    public static final String TABLE_NAME = "Tasks";
    public static final int VERSION = 1;
    public static final String KEY_FIELD_ID = "_id";
    public static final String FIELD_DESCRIPTION = "description";
    public static final String FIELD_IS_DONE = "is_done";

    /**
     * Adds a new task to the database
     * @param task The new task to be added
     * @return The newly assigned id
     */
    public long addTask(Task task) {
        long id;

        // Decide whether we are reading or writing to/from DB
        // For adding tasks, we're writing to DB
        SQLiteDatabase db = getWritableDatabase();

        // When we add tasks, use data structure ContentValues (key, value) pairs
        ContentValues values = new ContentValues();

        // Set up key/value pairs
        values.put(FIELD_DESCRIPTION, task.getDescription());
        values.put(FIELD_IS_DONE, task.isDone() ? 1 : 0);

        // Insert data
        id = db.insert(TABLE_NAME, null, values);

        // Close connection to database
        db.close();

        return id;
    }

    public List<Task> getAllTasks() {

        List<Task> allTasks = new ArrayList<>();

        // Get tasks from database
        SQLiteDatabase db = getReadableDatabase();

        // Query the database to retrieve all records
        // Store in data structure Cursor
        Cursor cursor = db.query(TABLE_NAME,
                new String[] {KEY_FIELD_ID,FIELD_DESCRIPTION, FIELD_IS_DONE},
                null, null, null, null, null);

        // Loop through Cursor to make tasks and build a list
        if (cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(0);
                String description = cursor.getString(1);
                boolean isDone = cursor.getInt(2) == 1;
                allTasks.add(new Task(id, description, isDone));
            } while (cursor.moveToNext());
        }

        // Close cursor
        cursor.close();

        // Close connection
        db.close();
        return allTasks;
    }

    public void clearAllTasks() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSQL = "CREATE TABLE IF NOT EXISTS "
                + TABLE_NAME + "("
                + KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_DESCRIPTION + " TEXT, "
                + FIELD_IS_DONE + " INTEGER" + ")";

        Log.i(DATABASE_NAME, createSQL);
        db.execSQL(createSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop old table, recreate the new
        if (newVersion > oldVersion) {
            String dropSQL = "DROP TABLE IF EXISTS " + TABLE_NAME;
            Log.i(DATABASE_NAME, dropSQL);
            db.execSQL(dropSQL);

            onCreate(db);
        }
    }
}
