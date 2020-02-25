package edu.miracosta.cs134.kwebb.todo2day;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import edu.miracosta.cs134.kwebb.todo2day.model.DBHelper;
import edu.miracosta.cs134.kwebb.todo2day.model.Task;

public class MainActivity extends AppCompatActivity {

    private DBHelper mDB;
    TaskListAdapter mTaskListAdapter;
    ListView mTaskListView;
    private List<Task> mAllTasks;
    EditText mTaskEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTaskEditText = findViewById(R.id.taskEditText);

        // Wire up DB
        mDB = new DBHelper(this);
        // mDB.clearAllTasks();

        // Dummy data
        // mDB.addTask(new Task("Finish CS150 homework"));
        // mDB.addTask(new Task("Write essay"));
        // mDB.addTask(new Task("Raid"));
        // mDB.addTask(new Task("Eat"));

        mAllTasks = mDB.getAllTasks();
        // Loop through tasks and print to log
        for (Task t : mAllTasks)
            Log.i("ToDo2Day", t.toString());

        // Instantiate list adapter
        mTaskListView = findViewById(R.id.taskListView);
        mTaskListAdapter = new TaskListAdapter(this, R.layout.task_item, mAllTasks);
        mTaskListView.setAdapter(mTaskListAdapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDB.close();
    }

    public void addTask(View v) {
        String description = mTaskEditText.getText().toString();

        // Check if empty description before creating task
        if (description.equals("")) {
            Toast.makeText(this, "Give the task a name!", Toast.LENGTH_SHORT).show();
        }
        else {
            Task newTask = new Task(description); // id = -1
            long id = mDB.addTask(newTask); // id set
            newTask.setId(id); // Set on newTask
            mAllTasks.add(newTask); // add to list

            // Refresh adapter to populate list
            mTaskListAdapter.notifyDataSetChanged();
        }
    }

    public void clearAllTasks(View v) {
        mDB.clearAllTasks();
        mAllTasks.clear();

        // Update adapter
        mTaskListAdapter.notifyDataSetChanged();
    }

    public void changeTaskStatus(View v) {
        CheckBox boxClicked = (CheckBox) v;

        Task checkedTask = (Task) boxClicked.getTag();

        // Toggle the done field of the task
        checkedTask.setDone(!checkedTask.isDone());

        // Update task in db
        mDB.updateTask(checkedTask);

        // Cross out checked text for fun
        if (boxClicked.isChecked())
            boxClicked.setPaintFlags(boxClicked.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        else
            boxClicked.setPaintFlags(boxClicked.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
    }
}
