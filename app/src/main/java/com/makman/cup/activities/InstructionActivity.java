package com.makman.cup.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.makman.cup.R;
import com.makman.cup.beans.FrenchPressTasks;
import com.makman.cup.beans.Task;
import com.makman.cup.views.CompletionView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InstructionActivity extends AppCompatActivity {
    public static final String ARG_BREW_TYPE = "arg_brew_type";
    public static final int FRENCH_PRESS = 0;

    private CompletionView mCompletion;
    private TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);
        mCompletion = (CompletionView) findViewById(R.id.activity_instruction_completionview);
        mTitle = (TextView) findViewById(R.id.activity_instruction_textview_title);
        int brewType = getIntent().getIntExtra(ARG_BREW_TYPE, -1);
        Task[] tasks;
        switch (brewType){
            case FRENCH_PRESS:
                tasks = FrenchPressTasks.getTasks();
                mTitle.setText("french press");
                break;
            default:
                tasks = FrenchPressTasks.getTasks();
                break;
        }
        mCompletion.setTasks(tasks);

    }


}
