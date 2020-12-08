package com.wgabrechnung.manageme2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import com.wgabrechnung.manageme2.database.DatabaseKonto;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;


public class HTTP_REQUEST extends AsyncTask<String, Void, String>  {

    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private static Integer MODE;

    public HTTP_REQUEST(Context actCon, Integer mode){
        context = actCon;
        MODE = mode;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {

        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            StringBuilder sb = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String json;
            while ((json = bufferedReader.readLine()) != null) {
                sb.append(json + "\n");
            }
            return sb.toString().trim();
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        try {
            JSONObject obj = new JSONObject(result);
            switch(MODE){
                //registrierung
                case 0:
                    registrierung(obj);
                    break;
                //LogIn
                case 1:
                    login(obj);
                    break;
                //Kontenrundruf
                case 2:
                    Kontenrundruf(obj);
                    break;
                //neues Projekt erstellen
                case 3:
                    break;
                //neues Projekt hinzufügen
                case 4:
                    break;
                default:
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public void registrierung(JSONObject obj){
        try{
            //Bei fehler einen Toast ausgeben
            if(obj.length() == 0){
                Toast.makeText(context, "Fehler beim laden der User Daten!", Toast.LENGTH_SHORT).show();
            }else {
                if(obj.getBoolean("REGISTER_SUCCESS")){
                    LogInActivity logIn = new LogInActivity();
                    logIn.login(obj.getString("MAIL"),obj.getString("PASSWORT"),context);
                }else{
                    if(obj.has("MAIL")){
                        Toast.makeText(context, "Fehler beim automatischen einloggen. Bitte versuche es über die LogIn Maske", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(context, "Es ist ein Fehler bei der Registrierung aufgetreten.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void login(JSONObject obj){
        try{
            if(obj.length() == 0){
                //Fehler anzeigen
                Toast.makeText(context, "Fehler beim laden der User Daten!", Toast.LENGTH_SHORT).show();
            }else {
                if(obj.getBoolean("LOGIN_SUCCESS")){

                    //Login Key setzen
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    //wenn der Login erfolgreich war die startseite zeigen
                    Intent activityStartseite = new Intent(context, StartseiteActivity.class);
                    activityStartseite.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(activityStartseite);

                }else{

                    Toast.makeText(context, "Es ist ein Fehler beim LogIn aufgetreten.", Toast.LENGTH_LONG).show();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void Kontenrundruf(JSONObject obj) throws JSONException {

        DatabaseKonto dbKonto = new DatabaseKonto(context);

        //-- damit Status nicht genommen wird
        int length = obj.length();
        length--;

        for(Integer i = 0; i<length; i++){

            String key = i.toString();
            if ( obj.get(key) instanceof JSONObject ) {
                JSONObject uObj = (JSONObject) obj.get(key);

                Double betrag = uObj.getDouble("BETRAG");
                String vzweck = uObj.getString("VZWECK");
                String art = uObj.getString("ART");
                String name = uObj.getString("NAME");
                String date = uObj.getString("DATE");
                String credit = uObj.getString("CREDIT_DEBIT");

                dbKonto.addDataset(betrag, vzweck, art, name, date, credit);
            }
        }

        Toast.makeText(context,"Kontenrundruf beendet",Toast.LENGTH_SHORT).show();

    }

}
