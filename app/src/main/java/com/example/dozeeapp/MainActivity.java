package com.example.dozeeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ShareCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
Button good,weak,fresh;
int Snapshotdate=1;
private int currentfragment=1;
    Fragment selected;
    Cursor cur2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Date date=new Date(); // your date
// Choose time zone in which you want to interpret your Date
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        final int day = cal.get(Calendar.DAY_OF_MONTH);
        String currentday= String.valueOf(7);





        final TextView message = (TextView)findViewById(R.id.message);
        message.setText("Hello\n How are you felling today?");


        ImageButton ig = (ImageButton) findViewById(R.id.share);
        ig.setEnabled(true);
        ig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
writetofile();
try{
//    Intent intentShareFile = new Intent(Intent.ACTION_SEND);
//    String path=MainActivity.this.getFilesDir().getAbsolutePath();
//    File fileWithinMyDir = new File(path+"/report.txt");
//
//    if(fileWithinMyDir.exists()) {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
//        intentShareFile.setType("application/pdf");
//        intentShareFile.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://"+"report.txt"));
//
//        intentShareFile.putExtra(Intent.EXTRA_SUBJECT,
//                "Sharing File...");
//        intentShareFile.putExtra(Intent.EXTRA_TEXT, "Sharing File...");
//
//        startActivity(Intent.createChooser(intentShareFile, "Share File"));
//
//
    Intent intentShareFile = new Intent(Intent.ACTION_SEND);
    String path=MainActivity.this.getFilesDir().getAbsolutePath();
    File fileWithinMyDir = new File(path+"/report");

    if(fileWithinMyDir.exists()) {


String data = new String();
if(currentfragment==1)
    data="Daily Report\n";
if(currentfragment==2)
    data="Weekly Report\n";
if(currentfragment==3)
    data="Monthly Report\n";

try{
        Scanner fScn = new Scanner(new File(path+"/report"));
        while(fScn.hasNextLine()) {   //Can also use a BufferedReader
            data += fScn.nextLine()+"\n";

        } }catch (FileNotFoundException e) {
    e.printStackTrace();
    Toast.makeText(MainActivity.this,"File sharing failed, try again later",Toast.LENGTH_LONG).show();
}

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, data);
        sendIntent.setType("text/plain");



        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }


}catch (Exception e){
    Toast.makeText(MainActivity.this,"File sharing failed, try again later",Toast.LENGTH_LONG).show();
    e.printStackTrace();
}

}
            });

       fresh = (Button)findViewById(R.id.fresh);
        fresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar=Calendar.getInstance();
                SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
                String currentTime=sdf.format(calendar.getTime());
                SharedPreferences prefs = getSharedPreferences(currentTime, MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(currentTime,"Fresh");
                editor.commit();
                fresh.setEnabled(false);
                good.setEnabled(true);
                weak.setEnabled(true);
                message.setText("Great! Have a good day");
            }
        });

 good = (Button)findViewById(R.id.good);
        good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar calendar=Calendar.getInstance();
                SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
                String currentTime=sdf.format(calendar.getTime());
                SharedPreferences prefs = getSharedPreferences(currentTime, MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(currentTime,"Good");
                editor.commit();
                message.setText("Good to know. Have a good day");
                good.setEnabled(false);
                fresh.setEnabled(true);
                weak.setEnabled(true);
            }
        });

        weak = (Button) findViewById(R.id.weak);
        weak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar=Calendar.getInstance();
                SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
                String currentTime=sdf.format(calendar.getTime());
                SharedPreferences prefs = getSharedPreferences(currentTime, MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(currentTime,"Weak");
                editor.commit();
                message.setText("Oh take care.");
                weak.setEnabled(false);
                fresh.setEnabled(true);
                good.setEnabled(true);
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new DetailsFragment(1)).commit();
        Button daily = (Button)findViewById(R.id.daily);
        daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentfragment=1;
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new DetailsFragment(1)).commit();
            }
        });

        Button monthly = (Button)findViewById(R.id.monthly);
        monthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentfragment=3;
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new DetailsFragmentMonthly()).commit();
            }
        });


        Button weekly = (Button)findViewById(R.id.weekly);
        weekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentfragment=2;
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new DetailsFragmentWeekly()).commit();
            }
        });



//        CircularProgressBar circularProgressBar = (CircularProgressBar) findViewById(R.id.heart);
//        circularProgressBar.setColor(ContextCompat.getColor(this, R.color.safe));
//        circularProgressBar.setBackgroundColor(ContextCompat.getColor(this, R.color.gray));
//        int animationDuration = 5000; // 2500ms = 2,5s
//        circularProgressBar.setProgressWithAnimation(65, animationDuration);
//
//
//        CircularProgressBar circularProgressBar1 = (CircularProgressBar) findViewById(R.id.bpmmeasure);
//        circularProgressBar1.setColor(ContextCompat.getColor(this, R.color.danger));
//        circularProgressBar1.setBackgroundColor(ContextCompat.getColor(this, R.color.gray));
//        int animationDuration1 = 5000; // 2500ms = 2,5s
//        circularProgressBar1.setProgressWithAnimation(19, animationDuration1);
//
//        CircularProgressBar circularProgressBar2 = (CircularProgressBar) findViewById(R.id.bpmmeasure);
//        circularProgressBar2.setColor(ContextCompat.getColor(this, R.color.danger));
//        circularProgressBar2.setBackgroundColor(ContextCompat.getColor(this, R.color.gray));
//        int animationDuration2 = 5000; // 2500ms = 2,5s
//        circularProgressBar2.setProgressWithAnimation(80, animationDuration2);
//
//
//        CircularProgressBar circularProgressBar5 = (CircularProgressBar) findViewById(R.id.oxygenmeasure);
//        circularProgressBar5.setColor(ContextCompat.getColor(this, R.color.average));
//        circularProgressBar5.setBackgroundColor(ContextCompat.getColor(this, R.color.gray));
//        int animationDuration5 = 5000; // 2500ms = 2,5s
//        circularProgressBar5.setProgressWithAnimation(80, animationDuration5);
//
//
//        CircularProgressBar circularProgressBar3 = (CircularProgressBar) findViewById(R.id.brainmeasurement);
//        circularProgressBar3.setColor(ContextCompat.getColor(this, R.color.average));
//        circularProgressBar3.setBackgroundColor(ContextCompat.getColor(this, R.color.gray));
//        int animationDuration3 = 5000; // 2500ms = 2,5s
//        circularProgressBar3.setProgressWithAnimation(80, animationDuration3);
//
//
//        CircularProgressBar circularProgressBar4 = (CircularProgressBar) findViewById(R.id.sleepmeasurement);
//        circularProgressBar4.setColor(ContextCompat.getColor(this, R.color.danger));
//        circularProgressBar4.setBackgroundColor(ContextCompat.getColor(this, R.color.gray));
//        int animationDuration4 = 5000; // 2500ms = 2,5s
//        circularProgressBar4.setProgressWithAnimation(80, animationDuration4);

        findViewById(R.id.call).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "+91-8884436933"));
                if (ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    startActivity(intent);

                }
                else{

                    Toast.makeText(MainActivity.this,"Need Call permission",Toast.LENGTH_LONG).show();
                }

            }
        });



    BottomAppBar bottomAppBar = findViewById(R.id.bottombar);


        //set bottom bar to Action bar as it is similar like Toolbar
        setSupportActionBar(bottomAppBar);

        bottomAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.email:
                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                                "mailto","support@dozee.io ", null));
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Rahul - Need support with my Dozee app");
                        emailIntent.putExtra(Intent.EXTRA_TEXT, "");
                        startActivity(Intent.createChooser(emailIntent, "Send email..."));

                        break;
                    case R.id.whatsapp:
                        PackageManager pm=getPackageManager();
                        try {

                            String url = "https://api.whatsapp.com/send?phone="+"+91-8884436933";
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(url));
                            startActivity(i);

                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                                    .show();
                        }
                        break;

                }
                return false;
            }
        });

    }

    private void writetofile() {

        Date date=new Date(); // your date
// Choose time zone in which you want to interpret your Date
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        String currentday= String.valueOf(10);

        UserdetailsDB hb = new UserdetailsDB(MainActivity.this);
        SQLiteDatabase db=hb.getReadableDatabase();

        if(currentfragment==1){
            String snapmonth=String.valueOf(month);
            String snapdate=String.valueOf(Snapshotdate);
         cur2 = db.rawQuery("SELECT  * FROM " + "hotwords" + " WHERE "+" day = ? AND month = ?"   ,new String[] {snapdate,snapmonth});


        }
        else if(currentfragment==2){
            String currentday1 = String.valueOf(day - 1);
            String currenday2 = String.valueOf(day - 2);
            String currentday3 = String.valueOf(day - 3);
            String currentday4 = String.valueOf(day - 4);
            String currentday5 = String.valueOf(day - 5);
            String currenday6 = String.valueOf(day - 6);
             cur2 = db.rawQuery("SELECT  * FROM " + "hotwords" + " WHERE "+" day = ? OR day = ? OR day = ? OR day = ? OR day = ? OR day = ? OR day = ?"   ,new String[] {currentday,currentday1,currenday2,currentday3,currentday4,currentday5,currenday6});

        }
        else{
            String currentmonth= String.valueOf(month);
             cur2 = db.rawQuery("SELECT  * FROM " + "hotwords" + " WHERE "+" month = ?"   ,new String[] {currentmonth});
        }
        int noofhearvalues=0;
        int noofbreathevalues=0;
        int noofoxygenvalues=0;
        int noofsystolevalues=0;
        int noofdiastolevalues=0;
        int noofrecoveryvalues=0;
        int noofsleepscores=0;
        int totalheartrate=0;
        int TotalBreatherate=0;
        int oxygenmeasure=0;
        int TotalSystole=0;
        int TotalDiastole=0;
        int TotalRecovery=0;
        int TotalSleepscore=0;
        while (cur2.moveToNext()){
            String heartrate=cur2.getString(cur2.getColumnIndex("Heartrate"));
            String Breatherate = cur2.getString(cur2.getColumnIndex("Breatherate"));
            String Oxygenmeasure = cur2.getString(cur2.getColumnIndex("Oxgenrate"));
            String Systole = cur2.getString(cur2.getColumnIndex("Systole"));
            String Diastole = cur2.getString(cur2.getColumnIndex("Diastole"));
            String Recovery = cur2.getString(cur2.getColumnIndex("Recovery"));
            String Sleepscore = cur2.getString(cur2.getColumnIndex("Sleepscore"));
            if(Integer.parseInt(heartrate)>0){
                noofhearvalues++;
                totalheartrate+=Integer.parseInt(heartrate);
            }
            if(Integer.parseInt(Breatherate)>0){
                noofbreathevalues++;
                TotalBreatherate+=Integer.parseInt(Breatherate);
            }
            if(Integer.parseInt(Oxygenmeasure)>0){
                noofoxygenvalues++;
                oxygenmeasure+=Integer.parseInt(Oxygenmeasure);
            }
            if(Integer.parseInt(Systole)>0){
                noofsystolevalues++;
                TotalSystole+=Integer.parseInt(Systole);
            }
            if(Integer.parseInt(Diastole)>0){
                noofdiastolevalues++;
                TotalDiastole+=Integer.parseInt(Diastole);
            }
            if(Integer.parseInt(Recovery)>0){
                noofrecoveryvalues++;
                TotalRecovery+=Integer.parseInt(Recovery);
            }
            if(Integer.parseInt(Sleepscore)>0) {
                noofsleepscores++;
                TotalSleepscore += Integer.parseInt(Sleepscore);

            }
        }

        int averageheartrate=totalheartrate/noofhearvalues;
        int averagebreatherate=TotalBreatherate/noofbreathevalues;
        int averageoxygenmeasure=oxygenmeasure/noofoxygenvalues;
        int averagesystole=TotalSystole/noofsystolevalues;
        int averagediastole=TotalDiastole/noofdiastolevalues;
        int averagerecovery=TotalRecovery/noofrecoveryvalues;
        int averagesleep=TotalSleepscore/noofsleepscores;
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        String currentTime=sdf.format(calendar.getTime());
        SharedPreferences prefs = MainActivity.this.getSharedPreferences(currentTime, MODE_PRIVATE);
        String status = prefs.getString(currentTime,"Status not available");
        try {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(MainActivity.this.openFileOutput("report", Context.MODE_PRIVATE));
            outputStreamWriter.write("Today Status of health:"+status+"\nAverage Heart Rate:"+averageheartrate+"\nAverage Breathe Rate:"+averagebreatherate+"\nAverage Oxygen Measure:"+averageoxygenmeasure+"\nAverage Systole:"+averagesystole+"\nAverage Diastole:"+averagediastole+"\nAverage Sleep Score:"+averagesleep+"\nAverage Recovery:"+averagerecovery);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }


    }

    //find id

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_list_of_words_drawer, menu);
        return super.onCreateOptionsMenu(menu);
    }



    //click event over Bottom bar menu item





}
