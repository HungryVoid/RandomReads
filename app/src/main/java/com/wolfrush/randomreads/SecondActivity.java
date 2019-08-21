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
import android.widget.ToggleButton;

public class SecondActivity extends AppCompatActivity {

    SharedPref sharedpref;
    ToggleButton toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedpref = new SharedPref(this);
        if (sharedpref.loadNightModeState()==true){
            setTheme(R.style.darktheme);
        }
        else setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        toggle = (ToggleButton) findViewById(R.id.toggle);


        String savedExtra = getIntent().getStringExtra("text_thing");
        final TextView story_text = (TextView) findViewById(R.id.story_text);
        story_text.setText(savedExtra);

        story_text.setMovementMethod(new ScrollingMovementMethod());


        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked){
                    story_text.setTextSize(40);
                }
                else
                {
                    story_text.setTextSize(20);
                }

            }

        });


    }



}
