package com.wolfrush.randomreads;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.text.method.ScrollingMovementMethod;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    SharedPref sharedpref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedpref = new SharedPref(this);
        if (sharedpref.loadNightModeState()==true){
            setTheme(R.style.darktheme);
        }
        else setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        String savedExtra = getIntent().getStringExtra("text_thing");
        TextView story_text = (TextView) findViewById(R.id.story_text);
        story_text.setText(savedExtra);

        story_text.setMovementMethod(new ScrollingMovementMethod());

    }

}
