package edu.miracosta.cs134.kwebb.todo2day.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

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
     */
    public void addTask(Task task) {
        // Decide whether we are reading or writing to/from DB
        // For adding tasks, we're writing to DB
        SQLiteDatabase db = getWritableDatabase();

        // When we add tasks, use data structure ContentValues (key, value) pairs
        ContentValues values = new ContentValues();

        // Set up key/value pairs
        values.put(FIELD_DESCRIPTION, task.getDescription());
        values.put(FIELD_IS_DONE, task.isDone() ? 1 : 0);

        // Insert data
        db.insert(TABLE_NAME, null, values);

        // Close connection to database
        db.close();
    }

    public List<Task> getAllTasks() {
        List<Task> taskList = new LinkedList<>();

        return taskList;
    }

    public void deleteTask(Task task) {

    }

    public void updateTask(Task task) {

    }

    public void getTask(int id) {

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
