package com.wolfrush.randomreads;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {


    private Switch night_mode;
    SharedPref sharedpref;
    Button random_story;
    Button genre_1;
    Button genre_2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedpref = new SharedPref(this);
        if (sharedpref.loadNightModeState()==true){
            setTheme(R.style.darktheme);
        }
        else setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        night_mode=(Switch)findViewById(R.id.night_mode);
        if (sharedpref.loadNightModeState()==true){
            night_mode.setChecked(true);
        }
        night_mode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    sharedpref.setNightModeState(true);
                    restartApp();
                }
                else{
                    sharedpref.setNightModeState(false);
                    restartApp();
                }
            }
        });

        random_story = (Button) findViewById(R.id.random_story);

        random_story.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = "";

                Random a = new Random();
                int folderStart = 1;
                int folderEnd = 4;
                int randomFolder = a.nextInt(folderEnd - folderStart) + folderStart;

                Random r = new Random();
                int fileStart = 1;
                int fileEnd = 4;
                int randomFile = r.nextInt(fileEnd - fileStart) + fileStart;


                try {
                    InputStream is = getAssets().open(randomFolder + "/" + randomFile + ".txt");
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



    }

    public void restartApp(){
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
        finish();
    }

}
