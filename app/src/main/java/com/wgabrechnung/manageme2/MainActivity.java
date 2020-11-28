package com.wgabrechnung.manageme2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.wgabrechnung.manageme2.database.DatabaseProjekte;

public class MainActivity extends AppCompatActivity {

    String USER_ID;
    Boolean STAY_LOGGED_IN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("STAY_LOGGED_IN", false);

        USER_ID = sharedPreferences.getString("UNIQUE_ID", "noID");
        STAY_LOGGED_IN = sharedPreferences.getBoolean("STAY_LOGGED_IN", false);

        //wenn keine id gestezt ist einen Dialog um alle SystemVariablen zu setzen
        if(USER_ID.equals("noID") || !STAY_LOGGED_IN){

            if(USER_ID.equals("noID")){
                //Zur Registrierung weiterleiten
                Intent activityRegister = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(activityRegister);
            }else{
                //Zum logIn weiterleiten
                Intent activityLogIn = new Intent(getApplicationContext(), LogInActivity.class);
                startActivity(activityLogIn);
            }

        }else{
            //und Startseite öffnen
            Intent activityStartseite = new Intent(getApplicationContext(), StartseiteActivity.class);
            startActivity(activityStartseite);
        }

    }
}