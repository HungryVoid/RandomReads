package com.hungryvoid.randomreads;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Settings extends AppCompatActivity {

    private ToggleButton night_mode;
    SharedPref sharedpref;
    Button review;
    Button disclaimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {//The same Night Mode code as the other activities

        sharedpref = new SharedPref(this);
        if (sharedpref.loadNightModeState()==true){
            setTheme(R.style.darktheme);
        }
        else setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        night_mode=(ToggleButton) findViewById(R.id.night_mode);//This is the switch that controls Night Mode
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

        ConstraintLayout bg = (ConstraintLayout) findViewById(R.id.bg); //This references the background in the activity_settings XML


        ImageButton go_back = (ImageButton)findViewById(R.id.go_back);

        go_back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(myIntent);
                finish();
            }


        });

        disclaimer=(Button) findViewById(R.id.disclaimer);

        disclaimer.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent disIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://hungryvoid.github.io/PrivacyPolicy/")); //This line connects the button to the website
                startActivity(disIntent);
            }
        });

        review=(Button) findViewById(R.id.review);

        review.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse("market://details?id=" + getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(Settings.this, " Could Not Load", Toast.LENGTH_LONG).show();
                }
            }

        });

    }


    public void restartApp(){//This is necessary to restart the app and boot it with Night Mode on
        Intent i = new Intent(getApplicationContext(),Settings.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(myIntent);
        finish();
    }

}
