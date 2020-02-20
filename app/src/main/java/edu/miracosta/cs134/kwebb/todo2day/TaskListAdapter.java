package edu.miracosta.cs134.kwebb.todo2day;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import edu.miracosta.cs134.kwebb.todo2day.model.Task;

public class TaskListAdapter extends ArrayAdapter<Task> {

    private Context mContext;
    private int mResource;
    private List<Task> mAllTasks;

    public TaskListAdapter(@NonNull Context context, int resource, @NonNull List<Task> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        mAllTasks = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResource, null);

        CheckBox isDoneCheckBox = view.findViewById(R.id.isDoneCheckBox);

        Task selectedTask = mAllTasks.get(position);

        // Populate properties of check box from task
        isDoneCheckBox.setText(selectedTask.getDescription());
        isDoneCheckBox.setChecked(selectedTask.isDone());

        return view;
    }
}
