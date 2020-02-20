package edu.miracosta.cs134.kwebb.todo2day.model;

public class Task {

    private long mId;
    private String mDescription;
    private boolean mIsDone;

    public Task(long id, String description, boolean isDone) {
        mId = id;
        mDescription = description;
        mIsDone = isDone;
    }

    public Task(String description, boolean isDone) {
        this(-1, description, isDone);
    }

    public Task(String description) {
        this(-1, description, false);
    }

    public long getId() {
        return mId;
    }

    public void setId(long mId) {
        this.mId = mId;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public boolean isDone() {
        return mIsDone;
    }

    public void setDone(boolean mIsDone) {
        this.mIsDone = mIsDone;
    }

    @Override
    public String toString() {
        return "Task{" +
                "Id=" + mId +
                ", Description='" + mDescription + '\'' +
                ", IsDone=" + mIsDone +
                '}';
    }
}
