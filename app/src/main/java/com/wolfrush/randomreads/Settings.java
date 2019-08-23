package com.wolfrush.randomreads;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.ToggleButton;

public class Settings extends AppCompatActivity {

    private ToggleButton night_mode;
    SharedPref sharedpref;
    Button go_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        sharedpref = new SharedPref(this);
        if (sharedpref.loadNightModeState()==true){
            setTheme(R.style.darktheme);
        }
        else setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        night_mode=(ToggleButton)findViewById(R.id.night_mode);
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


        go_back = (Button) findViewById(R.id.go_back);

        go_back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(myIntent);
            }


        });


    }


    public void restartApp(){
        Intent i = new Intent(getApplicationContext(),Settings.class);
        startActivity(i);
        finish();
    }

}
