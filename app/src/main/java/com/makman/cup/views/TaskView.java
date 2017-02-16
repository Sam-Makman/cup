package com.makman.cup.views;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.makman.cup.R;
import com.makman.cup.beans.Task;

import java.io.Console;
import java.util.Timer;

import static android.R.attr.value;
import static android.content.ContentValues.TAG;

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

        String fontPath = "fonts/AbrilFatface-Regular.ttf";
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), fontPath);

        mTaskCompletion = TASK_INCOMPLETE;
        mTitle = (TextView) findViewById(R.id.view_task_textview_title);
        mTitle.setTypeface(typeface);
        mDescription = (TextView) findViewById(R.id.view_task_textview_description);

        mImage = (ImageView) findViewById(R.id.view_task_imageview_incomplete);
        mButton = (Button) findViewById(R.id.view_task_button);
        mTimer = (TextView) findViewById(R.id.view_task_textview_timer);
        mTimer.setTypeface(typeface);
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




        mDescription.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mDescription.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                slideToBottom(mDescription);
            }
        });

        mButton.setAlpha(0.0f);
        mButton.animate().alpha(1.0f).setDuration(1000);

        ValueAnimator a = ValueAnimator.ofFloat(20, 40);
        a.setDuration(1000);

        a.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float height = ((float)valueAnimator.getAnimatedValue() - 20)/20;
                mTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, (float)valueAnimator.getAnimatedValue());
            }
        });
        a.start();

    }


    // To animate view slide out from top to bottom
    public void slideToBottom(View view){

        AnimationSet set = new AnimationSet(true);
        Animation trAnimation = new TranslateAnimation(0,0,-(view.getHeight()/2),0);
        trAnimation.setDuration(500);

        set.addAnimation(trAnimation);
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(500);
        set.addAnimation(anim);

        view.startAnimation(set);
    }


    // To animate view slide out from top to bottom
    public void slideToTop(View view){

        AnimationSet set = new AnimationSet(true);
        Animation trAnimation = new TranslateAnimation(0,0,0,-(view.getHeight()/2));
        trAnimation.setDuration(500);

        set.addAnimation(trAnimation);
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(500);
        set.addAnimation(anim);

        view.startAnimation(set);
    }


    public void complete(){
        mTaskCompletion = TASK_COMPLETE;
        mImage.setImageDrawable(getContext().getDrawable(R.drawable.ic_stage_complete));
        ValueAnimator a = ValueAnimator.ofFloat(40, 20);
        a.setDuration(1000);

        a.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, (float)valueAnimator.getAnimatedValue());
            }
        });

        ValueAnimator va = ValueAnimator.ofInt(100,0);
        va.setDuration(1000);

        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {


            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int val = (int) valueAnimator.getAnimatedValue();
                mButton.setHeight(val);
                mButton.setAlpha(val/100.0f);
                mDescription.setHeight(val);
                if(val <= 1){
                    mButton.setVisibility(GONE);
                    mDescription.setVisibility(GONE);
                }
            }
        });

        va.start();
        a.start();
    }

    public void incomplete(){
        mTaskCompletion = TASK_INCOMPLETE;
        mImage.setImageDrawable(getContext().getDrawable(R.drawable.ic_stage_incomplete));
        mDescription.setVisibility(View.GONE);
        mButton.setVisibility(View.GONE);
        mTimer.setVisibility(GONE);
        mTitle.setTextSize(20);
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
                    String time = sec/60 +":%02d";

                    mTimer.setText( String.format(time, sec%60));
                }

                public void onFinish() {
                    mListener.onTaskComplete();
                }
            }.start();

        }
    }
}
