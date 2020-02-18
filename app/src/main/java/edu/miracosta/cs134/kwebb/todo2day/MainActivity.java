package edu.miracosta.cs134.kwebb.todo2day;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import edu.miracosta.cs134.kwebb.todo2day.model.DBHelper;

public class MainActivity extends AppCompatActivity {

    private DBHelper mDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Wire up DB
        mDB = new DBHelper(this);
    }
}
