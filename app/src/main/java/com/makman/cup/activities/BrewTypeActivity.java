package com.makman.cup.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.makman.cup.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BrewTypeActivity extends AppCompatActivity {

    public static final String ARG_BREW = "arg_brew";

    @BindView(R.id.activity_brew_type_textview_aeropress)
    TextView mAero;

    @BindView(R.id.activity_brew_type_textview_pour_over)
    TextView mPour;

    @BindView(R.id.activity_brew_type_textview_french_press)
    TextView mFrench;

    @BindView(R.id.activity_brew_type_textview_espresso)
    TextView mEspresso;

    @BindView(R.id.activity_brew_type_textview_chemex)
    TextView mChem;

    @BindView(R.id.activity_brew_type_textview_cold_brew)
    TextView mCold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brew_type);
        ButterKnife.bind(this);

        //use custom font
        String fontPath = "fonts/AbrilFatface-Regular.ttf";
        Typeface typeface = Typeface.createFromAsset(getAssets(), fontPath);

        mAero.setTypeface(typeface);
        mPour.setTypeface(typeface);
        mFrench.setTypeface(typeface);
        mEspresso.setTypeface(typeface);
        mChem.setTypeface(typeface);
        mCold.setTypeface(typeface);
    }

//    @OnClick(R.id.activity_brew_type_button_aeropress)
//    void aeroClick(){
//        Intent i = new Intent(this, BrewActivity.class);
////        i.putExtra(ARG_BREW, Brew.AEROPRESS);
//        startActivity(i);
//    }
//
//    @OnClick(R.id.activity_brew_type_button_chemex)
//    void chemClick(){
//        Intent i = new Intent(this, BrewActivity.class);
////        i.putExtra(ARG_BREW, Brew.CHEMEX);
//        startActivity(i);
//    }
//
//    @OnClick(R.id.activity_brew_type_button_cold_brew)
//    void coldClick(){
//        Intent i = new Intent(this, BrewActivity.class);
////        i.putExtra(ARG_BREW, Brew.COLD_BREW);
//        startActivity(i);
//    }
//
//    @OnClick(R.id.activity_brew_type_button_espresso)
//    void espressoClick(){
//        Intent i = new Intent(this, BrewActivity.class);
////        i.putExtra(ARG_BREW, Brew.ESPRESSO);
//        startActivity(i);
//    }

    @OnClick(R.id.activity_brew_type_button_french_press)
    void frenchClick(){

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                BrewTypeActivity.this,
                new Pair<View, String>(findViewById(R.id.activity_brew_type_button_french_press),
                        getString(R.string.transition_french_image))
        );

        Intent i = new Intent(this, FrenchPressActivity.class);
        ActivityCompat.startActivity(this, i, options.toBundle());
    }

//    @OnClick(R.id.activity_brew_type_button_pour_over)
//    void pourClick(){
//        Intent i = new Intent(this, BrewActivity.class);
//        i.putExtra(ARG_BREW, Brew.POUR_OVER);
//        startActivity(i);
//    }
}
