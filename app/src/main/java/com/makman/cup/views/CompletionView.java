package com.makman.cup.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.makman.cup.R;
import com.makman.cup.beans.Task;

import java.util.ArrayList;

/**
 * Created by sam on 1/22/17.
 */

public class CompletionView extends LinearLayout implements TaskView.TaskViewListener {

    private Task[] mTasks;
    private LinearLayout mLayout;
    private Context mContext;
    private int active;
    private ArrayList<TaskView> mTaskViews;

    public CompletionView(Context context) {
        super(context);
        inflate(context, R.layout.view_completion, this);
        init(context);
    }

    public CompletionView(Context context, AttributeSet attrs){
        super(context, attrs);
        inflate(context, R.layout.view_completion, this);
        init(context);
    }


    public CompletionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.view_completion, this);
        init(context);
    }

    private void init(Context context){
        mLayout = (LinearLayout) findViewById(R.id.view_completion_layout);
        mContext = context;
        active = 0;
        mTaskViews = new ArrayList<TaskView>();
    }

    public void setTasks(Task[] tasks){
        mTasks = tasks;
        for(Task t : tasks){
            TaskView  taskView = new TaskView(mContext);
            taskView.setTask(t);
            taskView.setTaskViewListener(this);
            mLayout.addView(taskView);
            mTaskViews.add(taskView);
        }
        mTaskViews.get(active).active();
    }

    @Override
    public void onTaskComplete() {
        next();
    }

    private void next(){
        mTaskViews.get(active).complete();
        active++;
        if(active < mTaskViews.size()) {
            mTaskViews.get(active).active();
        }
    }
}
