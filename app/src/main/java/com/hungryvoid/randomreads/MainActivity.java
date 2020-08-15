package com.hungryvoid.randomreads;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageButton;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    SharedPref sharedpref; //Used for Night Mode


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedpref = new SharedPref(this); //This is the Night Mode Code
        if (sharedpref.loadNightModeState()==true){
            setTheme(R.style.darktheme);
        }
        else setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ConstraintLayout bg = (ConstraintLayout) findViewById(R.id.bg); //This references the background in the activity_main XML

        AlphaAnimation alpha = new AlphaAnimation(0f, 1f); //Fade in from 0 to 0.4 (XML has 0.4 set as the maximum)
        alpha.setDuration(1000); //Time it takes animation to complete
        alpha.setFillAfter(true); //Very important! Makes the full animation smooth
        bg.startAnimation(alpha); //Starts the animation



        ImageButton random_story = (ImageButton)findViewById(R.id.random_story);


        ObjectAnimator scaleDown = ObjectAnimator.ofPropertyValuesHolder( //This animates the Random Story button
                random_story,
                PropertyValuesHolder.ofFloat("scaleX", 1.2f),
                PropertyValuesHolder.ofFloat("scaleY", 1.2f));
        scaleDown.setDuration(1000); //Time it takes animation to complete

        scaleDown.setRepeatCount(ObjectAnimator.INFINITE); //Makes the animation infinite
        scaleDown.setRepeatMode(ObjectAnimator.REVERSE); //Reverses the animation to the stating point

        scaleDown.start();


        random_story.setOnClickListener(new View.OnClickListener() {
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


        ImageButton sett_butt = (ImageButton)findViewById(R.id.sett_butt);

        sett_butt.setOnClickListener(new View.OnClickListener(){
        public void onClick(View view){
            Intent myIntent = new Intent(getBaseContext(), Settings.class);
            startActivity(myIntent);
            finish();
            }


        });

    }



}
