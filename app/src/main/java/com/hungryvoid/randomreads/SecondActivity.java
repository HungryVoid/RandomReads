package com.hungryvoid.randomreads;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class SecondActivity extends AppCompatActivity {

    SharedPref sharedpref;
    ToggleButton toggle;

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

        ImageButton refresh = (ImageButton)findViewById(R.id.refresh);

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = "";

                Random r = new Random(); //Choose a random file within the folder. fileEnd must be one bigger than whats present.
                int fileStart = 1;
                int fileEnd = 101;
                int randomFile = r.nextInt(fileEnd - fileStart) + fileStart;


                try { //This is what opens up the chosen file
                    InputStream is = getAssets().open(randomFile + ".txt");
                    int size = is.available();
                    byte[] buffer = new byte[size];
                    is.read(buffer);
                    is.close();
                    text = new String(buffer);
                } catch (IOException ex){
                    ex.printStackTrace();
                }
                Intent myIntent = new Intent(getBaseContext(), SecondActivity.class); //This sends the file and moves us to the new screen to display it
                myIntent.putExtra("text_thing", text);
                startActivity(myIntent);
                finish();
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

    @Override //This overrides the phones back button to do the same thing as my back buttons
    public void onBackPressed() {
        Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(myIntent);
        finish();
    }

}
