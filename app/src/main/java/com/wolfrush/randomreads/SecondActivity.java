package com.wolfrush.randomreads;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    SharedPref sharedpref;
    RadioButton rad_sml;
    RadioButton rad_big;
    TextView story_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedpref = new SharedPref(this);
        if (sharedpref.loadNightModeState()==true){
            setTheme(R.style.darktheme);
        }
        else setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        rad_sml = (RadioButton) findViewById(R.id.rad_sml);
        rad_big = (RadioButton) findViewById(R.id.rad_big);


        String savedExtra = getIntent().getStringExtra("text_thing");
        final TextView story_text = (TextView) findViewById(R.id.story_text);
        story_text.setText(savedExtra);

        story_text.setMovementMethod(new ScrollingMovementMethod());

        rad_sml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                story_text.setTextSize(20);

            }

        });

        rad_big.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                story_text.setTextSize(40);

            }

        });

    }



}
