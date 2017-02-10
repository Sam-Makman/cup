package com.makman.cup.views;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.makman.cup.R;
import com.makman.cup.beans.Task;

import java.util.Timer;

/**
 * Created by sam on 1/28/17.
 */

public class TaskView extends LinearLayout implements View.OnClickListener {
    public static final int TASK_INCOMPLETE = 0;
    public static final int TASK_ACTIVE = 1;
    public static final int TASK_COMPLETE = 2;

    private int mTaskCompletion;

    private Context mContext;

    private TextView mTitle;
    private TextView mDescription;
    private TextView mTimer;
    private Button mButton;
    private ImageView mImage;

    private Task mTask;

    private TaskViewListener mListener;

    public interface TaskViewListener{
        public void onTaskComplete();
    }

    public void setTaskViewListener(TaskViewListener listener){
        mListener = listener;
    }

    public TaskView(Context context) {
        super(context);
        inflate(context, R.layout.view_task, this);
        init(context);
    }

    public TaskView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.view_task, this);
        init(context);
    }

    public TaskView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.view_task, this);
        init(context);
    }

    private void init(Context context){
        mTaskCompletion = TASK_INCOMPLETE;
        mTitle = (TextView) findViewById(R.id.view_task_textview_title);
        mDescription = (TextView) findViewById(R.id.view_task_textview_description);
        mImage = (ImageView) findViewById(R.id.view_task_imageview_incomplete);
        mButton = (Button) findViewById(R.id.view_task_button);
        mTimer = (TextView) findViewById(R.id.view_task_textview_timer);
        mButton.setOnClickListener(this);
        mContext = context;
    }

    public void setTask(Task task){
        mTask = task;
        mTitle.setText(task.getTitle());
        mDescription.setText(task.getDescription());
        if( task.getTimer() <=0 ){
            mButton.setText("Next");
        }else{
            String t;
            if(task.getTimer() > 60){
                if(task.getTimer() % 60 == 0){
                    t = task.getTimer()/60 + "min " ;
                }else{
                    t = task.getTimer()/60 + "min " + task.getTimer() % 60 + " sec timer";
                }
            }else{
                t = task.getTimer() % 60 + " sec timer";
            }
            mButton.setText(t);
        }
    }

    public void active(){
        mTaskCompletion = TASK_ACTIVE;
        mImage.setImageDrawable(getContext().getDrawable(R.drawable.ic_stage_active));
        mDescription.setVisibility(View.VISIBLE);
        mButton.setVisibility(View.VISIBLE);
        mTimer.setVisibility(GONE);
    }

    public void complete(){
        mTaskCompletion = TASK_COMPLETE;
        mImage.setImageDrawable(getContext().getDrawable(R.drawable.ic_stage_complete));
        mDescription.setVisibility(View.GONE);
        mButton.setVisibility(View.GONE);
        mTimer.setVisibility(GONE);
    }

    public void incomplete(){
        mTaskCompletion = TASK_INCOMPLETE;
        mImage.setImageDrawable(getContext().getDrawable(R.drawable.ic_stage_incomplete));
        mDescription.setVisibility(View.GONE);
        mButton.setVisibility(View.GONE);
        mTimer.setVisibility(GONE);
    }

    @Override
    public void onClick(View view) {
        if(mTask == null){
            return;
        }
        if(mTask.getTimer() <= 0){
            mListener.onTaskComplete();
        }else{
            mTimer.setVisibility(VISIBLE);
            mButton.setVisibility(GONE);
            new CountDownTimer(mTask.getTimer() * 1000, 1000) {

                public void onTick(long millisUntilFinished) {
                    long sec = millisUntilFinished/1000;
                    mTimer.setText( sec/60 + ":" + sec%60);
                }

                public void onFinish() {
                    mListener.onTaskComplete();
                }
            }.start();

        }
    }
}
