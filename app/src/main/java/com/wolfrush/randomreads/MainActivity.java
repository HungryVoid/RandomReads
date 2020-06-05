package com.wolfrush.randomreads;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {


    SharedPref sharedpref; //Used for Night Mode
    Button random_story;
    Button genre_1;
    Button genre_2;
    Button genre_3;
    Button sett_butt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedpref = new SharedPref(this); //This is the Night Mode Code
        if (sharedpref.loadNightModeState()==true){
            setTheme(R.style.darktheme);
        }
        else setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //This code is for randomly choosing the background image
        int[] img = new int[] {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5,
                R.drawable.img6, R.drawable.img7, R.drawable.img8, R.drawable.img9, R.drawable.img10}; //These are the images stored in drawable

        setContentView(R.layout.activity_main);
        ImageView bgImg = (ImageView)findViewById(R.id.bgImg); //This is the ImageView created in the XML

        int randImg = (int)(Math.random() * img.length); //Chooses a corresponding number to one of the images listed above at random

        bgImg.setBackgroundResource(img[randImg]);//This takes the number, applies it to a file and posts the image


        AlphaAnimation alpha = new AlphaAnimation(0f, 0.4f);
        alpha.setDuration(2000);
        alpha.setFillAfter(true);
        bgImg.startAnimation(alpha);


        random_story = (Button) findViewById(R.id.random_story);

        random_story.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = "";

                Random a = new Random(); //Choose a random folder
                int folderStart = 1;
                int folderEnd = 4;
                int randomFolder = a.nextInt(folderEnd - folderStart) + folderStart;

                Random r = new Random(); //Choose a random file within the folder
                int fileStart = 1;
                int fileEnd = 4;
                int randomFile = r.nextInt(fileEnd - fileStart) + fileStart;


                try { //This is what opens up the chosen file
                    InputStream is = getAssets().open(randomFolder + "/" + randomFile + ".txt");
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
            }
        });

//Everything below are just more buttons

        genre_1 = (Button) findViewById(R.id.genre_1);

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
            }
        });


        //sett_butt = (Button) findViewById(R.id.sett_butt);
        ImageButton sett_butt = (ImageButton)findViewById(R.id.sett_butt);
        sett_butt.setImageResource(R.drawable.sett_butt);

        sett_butt.setOnClickListener(new View.OnClickListener(){
        public void onClick(View view){
            Intent myIntent = new Intent(getBaseContext(), Settings.class);
            startActivity(myIntent);
            }


        });

    }



}
