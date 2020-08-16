package com.hungryvoid.randomreads;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Disclaimer extends AppCompatActivity {

    SharedPref sharedpref;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //The Night Mode code again
        sharedpref = new SharedPref(this);
        if (sharedpref.loadNightModeState() == true) {
            setTheme(R.style.darktheme);
        } else setTheme(R.style.AppTheme);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disclaimer);


        ImageButton go_back = (ImageButton)findViewById(R.id.go_back);

        go_back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent myIntent = new Intent(getBaseContext(), Settings.class);
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