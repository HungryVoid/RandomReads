package com.wolfrush.randomreads;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class SecondActivity extends AppCompatActivity {

    SharedPref sharedpref;
    ToggleButton toggle;
    Button go_back_story;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //The Night Mode code again
        sharedpref = new SharedPref(this);
        if (sharedpref.loadNightModeState()==true){
            setTheme(R.style.darktheme);
        }
        else setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        toggle = (ToggleButton) findViewById(R.id.toggle);


        String savedExtra = getIntent().getStringExtra("text_thing"); //This catches the sent file and displays it here
        final TextView story_text = (TextView) findViewById(R.id.story_text);
        story_text.setText(savedExtra);

        story_text.setMovementMethod(new ScrollingMovementMethod());//SCROLLING! SO EASY!


        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){//This controls the Zoom In/Out feature
                if(isChecked){
                    story_text.setTextSize(40);
                }
                else
                {
                    story_text.setTextSize(20);
                }

            }

        });

        ImageButton go_back_story = (ImageButton)findViewById(R.id.go_back_story);

        go_back_story.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(myIntent);
                finish();
            }


        });


    }

    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(myIntent);
        finish();
    }

}
