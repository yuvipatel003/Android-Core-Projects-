package com.yuvrajpatel.rxjava_application.Model;

public class Task {

    private String mDescription;
    private boolean mIsCompleted;
    private int mPriority;

    public Task(String description, boolean isCompleted, int priority){
        this.mDescription = description;
        this.mIsCompleted = isCompleted;
        this.mPriority = priority;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public Integer getPriority() {
        return mPriority;
    }

    public void setPriority(int mPriority) {
        this.mPriority = mPriority;
    }

    public void setIsCompleted(boolean mIsCompleted) {
        this.mIsCompleted = mIsCompleted;
    }

    public boolean getIsCompleted() {
        return this.mIsCompleted;
    }
}

