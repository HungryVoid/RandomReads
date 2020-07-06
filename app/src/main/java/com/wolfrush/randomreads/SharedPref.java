package com.wolfrush.randomreads;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {//The master Night Mode code... I think there's more in the XMLs?

    SharedPreferences mySharedPref;
    public SharedPref(Context context){
        mySharedPref = context.getSharedPreferences("filename",Context.MODE_PRIVATE);
    }

    public void setNightModeState(Boolean state) {
        SharedPreferences.Editor editor = mySharedPref.edit();
        editor.putBoolean("NightMode",state);
        editor.commit();
    }
    public Boolean loadNightModeState (){
        Boolean state = mySharedPref.getBoolean("NightMode",false);
        return state;
    }



}