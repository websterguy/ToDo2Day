package edu.miracosta.cs134.kwebb.todo2day.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    public void addTask(Task task) {

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
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
