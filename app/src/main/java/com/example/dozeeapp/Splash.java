package com.example.dozeeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new CallbackTask2().execute(dictionaryEntries());

    }
    public String dictionaryEntries() {
        return "https://f2a8b123-adbb-4c6a-beba-f3d3d42eea86.mock.pstmn.io/api/user/data/";
    }

    public class CallbackTask2 extends AsyncTask<String, Integer, String> {

        String myurl;

        protected void onPreExecute() {

            super.onPreExecute();

        }
        @Override
        protected String doInBackground(String... params) {
            myurl=params[0];
            //TODO: replace with your own app id and app key
            try {

                URL url = new URL(myurl);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("Accept","application/json");

                // read the output from the server
                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();

                String line = null;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }

                return stringBuilder.toString();

            }
            catch (Exception e) {
                e.printStackTrace();
                return e.toString();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            int flag=1;
            try{
                result=result.trim();
                UserdetailsDB hb = new UserdetailsDB(Splash.this);

                JSONArray js=new JSONArray(result);
                JSONObject js1;
                for(int i=0;i<js.length();i++){
                    String Systole = "0";
                    String Diastole = "0";
                    String heartrate="0";
                    String BreathRate="0";
                    String Oxygenrate="0";
                    String Recovery="0";
                    String sleepscore="0";
                    SQLiteDatabase sb = hb.getReadableDatabase();
                    js1 = (JSONObject)js.get(i);
                    if(js1.length()==0){

                    }
                    else{
                    js1=(JSONObject)js.get(i);
                    if(!js1.isNull("HeartRate")){
                     heartrate=js1.getString("HeartRate");
                    }
                    if(!js1.isNull("BreathRate")){
                     BreathRate = js1.getString("BreathRate");
                    }
                    if(!js1.isNull("O2")){
                     Oxygenrate= js1.getString("O2");
                    }
                    if(js1.has("Blood Pressure")){
                    JSONObject BP = js1.getJSONObject("Blood Pressure");
                        if(BP.length()!=0){
                            if(BP.length()==1){
                                if(!BP.isNull("Systole")){
                                    Systole = BP.getString("Systole");
                                }
                                if(!BP.isNull("Diastole")){
                                    Systole = BP.getString("Diastole");
                                }
                            }
                            else{
                                Systole = BP.getString("Systole");
                                Diastole = BP.getString("Diastole");
                            }
                        }
                    }
                    else if(js1.has("BP")){
                        JSONObject BP = js1.getJSONObject("BP");
                        if(BP.length()!=0){
                            if(BP.length()==1){
                                if(!BP.isNull("Systole")){
                                    Systole = BP.getString("Systole");
                                }
                                if(!BP.isNull("Diastole")){
                                    Systole = BP.getString("Diastole");
                                }
                            }
                            else{
                                Systole = BP.getString("Systole");
                                Diastole = BP.getString("Diastole");
                            }
                        }
                    }



                    if(js1.has("Recovery")){
                     Recovery = js1.getString("Recovery");
                    }
                    if(js1.has("sleepscore")){
                     sleepscore = js1.getString("sleepscore");
                    }
                    String timestamp=js1.getString("time");

                    Long timestamp1 = Long.parseLong(timestamp);
                    Date date = new Date();
                    date.setTime(timestamp1*1000);
                    int date1=date.getDate();
                    int month=date.getMonth();
                    int year=date.getYear();

                    Cursor cur2 = sb.rawQuery("SELECT  * FROM " + "hotwords" + " WHERE "+" id = ?"   ,new String[] {timestamp});
                    if(cur2.getCount()<=0)

                        hb.insertWordDetails(Integer.parseInt(timestamp),date1,month,year,heartrate,BreathRate,Oxygenrate,Systole,Diastole,Recovery,sleepscore);
                }}
                hb.close();
                Intent intent = new Intent(Splash.this,MainActivity.class);
                startActivity(intent);
                finish();

            }catch(Exception e){
                e.printStackTrace();
                Toast.makeText(Splash.this,"Something went wrong! Check Internet connnection and restart",Toast.LENGTH_LONG).show();

            }


        }
    }
}
