package com.makman.cup.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.makman.cup.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread closeActivity = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    Intent i = new Intent(SplashActivity.this, BrewTypeActivity.class);
                    startActivity(i);
                    finish();
                    overridePendingTransition(0, R.anim.fade_out);
                } catch (Exception e) {
                    e.getLocalizedMessage();
                }
            }
        });

        //use custom font
        TextView cup = (TextView) findViewById(R.id.activity_splash_text_view);
        String fontPath = "fonts/AbrilFatface-Regular.ttf";
        Typeface typeface = Typeface.createFromAsset(getAssets(), fontPath);
        cup.setTypeface(typeface);

        closeActivity.start();
        overridePendingTransition(0, R.anim.fade_out);
    }
}
