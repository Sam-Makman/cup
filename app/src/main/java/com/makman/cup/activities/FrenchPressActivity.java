package com.makman.cup.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.makman.cup.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class FrenchPressActivity extends AppCompatActivity {

    @BindView(R.id.activity_french_press_textview_takes)
    TextView mTakes;

    @BindView(R.id.activity_french_press_textview_taste)
    TextView mTaste;

    @BindView(R.id.activity_french_press_textview_title)
    TextView mTitle;

    @BindView(R.id.activity_french_press_textview_time)
    TextView mTime;

    @BindView(R.id.activity_french_press_textview_enough_for)
    TextView mEnough;

    @BindView(R.id.activity_french_press_textview_taste_profile)
    TextView mProfile;

    @BindView(R.id.activity_french_press_button_one)
    Button mOne;

    @BindView(R.id.activity_french_press_button_two)
    Button mTwo;

    @BindView(R.id.activity_french_press_button_three)
    Button mThree;

    @BindView(R.id.activity_french_press_button_four)
    Button mFour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_french_press);

        ButterKnife.bind(this);

        //use custom font
        String fatFacePath = "fonts/AbrilFatface-Regular.ttf";
        Typeface fatFace = Typeface.createFromAsset(getAssets(), fatFacePath);

        String openPath = "fonts/OpenSans-Regular.ttf";
        Typeface openSans = Typeface.createFromAsset(getAssets(), openPath);
        mTakes.setTypeface(openSans);
        mTaste.setTypeface(openSans);
        mTitle.setTypeface(fatFace);
        mTime.setTypeface(fatFace);
        mEnough.setTypeface(openSans);
        mProfile.setTypeface(fatFace);

    }

    @OnClick(R.id.activity_french_press_button_one)
    void oneClick(){
        mOne.setBackground(getDrawable(R.drawable.selected_button));
        mTwo.setBackground(getDrawable(R.drawable.unselected_button));
        mThree.setBackground(getDrawable(R.drawable.unselected_button));
        mFour.setBackground(getDrawable(R.drawable.unselected_button));
        mTime.setText("4 min");
    }

    @OnClick(R.id.activity_french_press_button_two)
    void twoClick(){
        mOne.setBackground(getDrawable(R.drawable.unselected_button));
        mTwo.setBackground(getDrawable(R.drawable.selected_button));
        mThree.setBackground(getDrawable(R.drawable.unselected_button));
        mFour.setBackground(getDrawable(R.drawable.unselected_button));
        mTime.setText("6 min");
    }


    @OnClick(R.id.activity_french_press_button_three)
    void threeClick(){
        mOne.setBackground(getDrawable(R.drawable.unselected_button));
        mTwo.setBackground(getDrawable(R.drawable.unselected_button));
        mThree.setBackground(getDrawable(R.drawable.selected_button));
        mFour.setBackground(getDrawable(R.drawable.unselected_button));
        mTime.setText("8 min");
    }


    @OnClick(R.id.activity_french_press_button_four)
    void fourClick(){
        mOne.setBackground(getDrawable(R.drawable.unselected_button));
        mTwo.setBackground(getDrawable(R.drawable.unselected_button));
        mThree.setBackground(getDrawable(R.drawable.unselected_button));
        mFour.setBackground(getDrawable(R.drawable.selected_button));
        mTime.setText("10 min");
    }

    @OnClick(R.id.activity_french_press_button_start)
    void frenchStartClick(){
      Intent intent = new Intent(this, InstructionActivity.class);
      intent.putExtra(InstructionActivity.ARG_BREW_TYPE, InstructionActivity.FRENCH_PRESS);
        startActivity(intent);
    }
}
