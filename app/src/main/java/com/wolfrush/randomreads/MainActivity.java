package com.wolfrush.randomreads;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    SharedPref sharedpref; //Used for Night Mode
    //Button random_story;
    //Button genre_1;
    //Button genre_2;
    //Button genre_3;
    //Button sett_butt;


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

        random_story.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = "";

                /*Random a = new Random(); //Choose a random folder
                int folderStart = 1;
                int folderEnd = 4;
                int randomFolder = a.nextInt(folderEnd - folderStart) + folderStart;*/

                Random r = new Random(); //Choose a random file within the folder
                int fileStart = 1;
                int fileEnd = 35;
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

//Everything below are just more buttons

        /*genre_1 = (Button) findViewById(R.id.genre_1);

        genre_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = "";

                Random r = new Random();
                int fileStart = 1;
                int fileEnd = 4;
                int randomFile = r.nextInt(fileEnd - fileStart) + fileStart;


                try {
                    InputStream is = getAssets().open("1/" + randomFile + ".txt");
                    int size = is.available();
                    byte[] buffer = new byte[size];
                    is.read(buffer);
                    is.close();
                    text = new String(buffer);
                } catch (IOException ex){
                    ex.printStackTrace();
                }
                Intent myIntent = new Intent(getBaseContext(), SecondActivity.class);
                myIntent.putExtra("text_thing", text);
                startActivity(myIntent);
                finish();
            }
        });

        genre_2 = (Button) findViewById(R.id.genre_2);

        genre_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = "";

                Random r = new Random();
                int fileStart = 1;
                int fileEnd = 4;
                int randomFile = r.nextInt(fileEnd - fileStart) + fileStart;


                try {
                    InputStream is = getAssets().open("2/" + randomFile + ".txt");
                    int size = is.available();
                    byte[] buffer = new byte[size];
                    is.read(buffer);
                    is.close();
                    text = new String(buffer);
                } catch (IOException ex){
                    ex.printStackTrace();
                }
                Intent myIntent = new Intent(getBaseContext(), SecondActivity.class);
                myIntent.putExtra("text_thing", text);
                startActivity(myIntent);
                finish();
            }
        });

        genre_3 = (Button) findViewById(R.id.genre_3);

        genre_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = "";

                Random r = new Random();
                int fileStart = 1;
                int fileEnd = 4;
                int randomFile = r.nextInt(fileEnd - fileStart) + fileStart;


                try {
                    InputStream is = getAssets().open("3/" + randomFile + ".txt");
                    int size = is.available();
                    byte[] buffer = new byte[size];
                    is.read(buffer);
                    is.close();
                    text = new String(buffer);
                } catch (IOException ex){
                    ex.printStackTrace();
                }
                Intent myIntent = new Intent(getBaseContext(), SecondActivity.class);
                myIntent.putExtra("text_thing", text);
                startActivity(myIntent);
                finish();
            }
        });*/


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
