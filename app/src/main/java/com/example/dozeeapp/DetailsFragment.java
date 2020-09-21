package com.example.dozeeapp;

import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hadiidbouk.charts.BarData;
import com.hadiidbouk.charts.ChartProgressBar;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.AsyncListUtil;

public class DetailsFragment extends Fragment {
    Dialog infodialog;
    Dialog graphdialog;
    int currentdayofweek;
    HashMap<Integer,String> daysofweek = new HashMap<>();
    int averageheartrate=0,averagebreatherate=0,averageoxygenmeasure=0,averagesystole=0,averagediastole=0,averagerecovery=0,averagesleep=0;
int day,month,year;
String currentday,currentmonth;
    static TextView hearmeasure;


   public DetailsFragment(int date){
day=date;

daysofweek.put(0,"Sun");
daysofweek.put(1,"Mon");
daysofweek.put(2,"Tue");
daysofweek.put(3,"Wed");
daysofweek.put(4,"Thu");
daysofweek.put(5,"Fri");
daysofweek.put(6,"Sat");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.framelayout1,container,false);
        ((MainActivity)getActivity()).Snapshotdate=day;

      hearmeasure = view.findViewById(R.id.heartmeasure);


        CardView heartmeasuregraph = (CardView) view.findViewById(R.id.heartmeasuregraph);
        heartmeasuregraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Date date=new Date(); // your date
// Choose time zone in which you want to interpret your Date
                Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
                cal.setTime(date);
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                currentmonth = String.valueOf(month);
                int day=cal.get(Calendar.DAY_OF_MONTH);
                currentdayofweek=cal.get(Calendar.DAY_OF_WEEK);
                if(day==1){
                    if(month==1|month==3|month==5|month==7|month==8|month==10|month==12)
                        day=32;
                    else
                        day=31;
                }
                String currentday= String.valueOf(7);
                if(day==1){
                    if(month==1|month==3|month==5|month==7|month==8|month==10|month==12)
                        day=32;
                    else
                        day=31;
                }
                String currentday1 = String.valueOf(day - 1);
                if(day==1){
                    if(month==1|month==3|month==5|month==7|month==8|month==10|month==12)
                        day=32;
                    else
                        day=31;
                }
                String currenday2 = String.valueOf(day - 2);
                if(day==1){
                    if(month==1|month==3|month==5|month==7|month==8|month==10|month==12)
                        day=32;
                    else
                        day=31;
                }
                String currentday3 = String.valueOf(day - 3);
                if(day==1){
                    if(month==1|month==3|month==5|month==7|month==8|month==10|month==12)
                        day=32;
                    else
                        day=31;
                }
                String currentday4 = String.valueOf(day - 4);
                if(day==1){
                    if(month==1|month==3|month==5|month==7|month==8|month==10|month==12)
                        day=32;
                    else
                        day=31;
                }
                String currentday5 = String.valueOf(day - 5);
                if(day==1){
                    if(month==1|month==3|month==5|month==7|month==8|month==10|month==12)
                        day=32;
                    else
                        day=31;
                }
                currentdayofweek=currentdayofweek-2;
                currentdayofweek%=7;
                String currenday6 = String.valueOf(day - 6);
                UserdetailsDB hb = new UserdetailsDB(getActivity());
                SQLiteDatabase db=hb.getReadableDatabase();
                ArrayList<BarData> dataList = new ArrayList<>();
                Cursor cur2 = db.rawQuery("SELECT  * FROM " + "hotwords" + " WHERE "+" day = ? OR day = ? OR day = ? OR day = ? OR day = ? OR day = ? OR day = ? LIMIT 7"   ,new String[] {currentday,currentday1,currenday2,currentday3,currentday4,currentday5,currenday6});
                while (cur2.moveToNext()){
                    String heartrategraph=cur2.getString(cur2.getColumnIndex("Heartrate"));
                    BarData data = new BarData(daysofweek.get(currentdayofweek++), Float.parseFloat(heartrategraph), heartrategraph);
                    dataList.add(data);
                    currentdayofweek%=7;

}



                graphdialog = new Dialog(getActivity());
                graphdialog.setContentView(R.layout.graphcard);


                final Button close = (Button) graphdialog.findViewById(R.id.close);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        graphdialog.dismiss();
                    }
                });








                ChartProgressBar mChart = (ChartProgressBar) graphdialog.findViewById(R.id.ChartProgressBar);
                mChart.setMaxValue(200);
                mChart.setDataList(dataList);
                mChart.build();

                mChart.setVisibility(View.VISIBLE);
                mChart.animate();


                graphdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                graphdialog.show();




            }
        });

        Date date=new Date(); // your date
// Choose time zone in which you want to interpret your Date
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        cal.setTime(date);
        DateFormat dateFormat = new SimpleDateFormat("MM-yyyy");




CardView bpm = (CardView) view.findViewById(R.id.bpmcard);
bpm.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {




        Date date=new Date(); // your date
// Choose time zone in which you want to interpret your Date
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day=cal.get(Calendar.DAY_OF_MONTH);
        currentdayofweek=cal.get(Calendar.DAY_OF_WEEK);
        if(day==1){
            if(month==1|month==3|month==5|month==7|month==8|month==10|month==12)
                day=32;
            else
                day=31;
        }
        String currentday= String.valueOf(7);
        if(day==1){
            if(month==1|month==3|month==5|month==7|month==8|month==10|month==12)
                day=32;
            else
                day=31;
        }
        String currentday1 = String.valueOf(day - 1);
        if(day==1){
            if(month==1|month==3|month==5|month==7|month==8|month==10|month==12)
                day=32;
            else
                day=31;
        }
        String currenday2 = String.valueOf(day - 2);
        if(day==1){
            if(month==1|month==3|month==5|month==7|month==8|month==10|month==12)
                day=32;
            else
                day=31;
        }
        String currentday3 = String.valueOf(day - 3);
        if(day==1){
            if(month==1|month==3|month==5|month==7|month==8|month==10|month==12)
                day=32;
            else
                day=31;
        }
        String currentday4 = String.valueOf(day - 4);
        if(day==1){
            if(month==1|month==3|month==5|month==7|month==8|month==10|month==12)
                day=32;
            else
                day=31;
        }
        String currentday5 = String.valueOf(day - 5);
        if(day==1){
            if(month==1|month==3|month==5|month==7|month==8|month==10|month==12)
                day=32;
            else
                day=31;
        }
        currentdayofweek=currentdayofweek-2;
        currentdayofweek%=7;
        String currenday6 = String.valueOf(day - 6);
        UserdetailsDB hb = new UserdetailsDB(getActivity());
        SQLiteDatabase db=hb.getReadableDatabase();
        ArrayList<BarData> dataList = new ArrayList<>();
        Cursor cur2 = db.rawQuery("SELECT  * FROM " + "hotwords" + " WHERE "+" day = ? OR day = ? OR day = ? OR day = ? OR day = ? OR day = ? OR day = ? LIMIT 7"   ,new String[] {currentday,currentday1,currenday2,currentday3,currentday4,currentday5,currenday6});
        while (cur2.moveToNext()){
            String Breathe=cur2.getString(cur2.getColumnIndex("Breatherate"));
            BarData data = new BarData(daysofweek.get(currentdayofweek++), Float.parseFloat(Breathe), Breathe);
            dataList.add(data);
            currentdayofweek%=7;

        }



        graphdialog = new Dialog(getActivity());
        graphdialog.setContentView(R.layout.graphcard);


        final Button close = (Button) graphdialog.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                graphdialog.dismiss();
            }
        });









        ChartProgressBar mChart = (ChartProgressBar) graphdialog.findViewById(R.id.ChartProgressBar);
        mChart.setMaxValue(50);
        mChart.setDataList(dataList);
        mChart.build();

        mChart.setVisibility(View.VISIBLE);
        mChart.animate();


        graphdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        graphdialog.show();





    }
});

        CardView Oxygen = (CardView) view.findViewById(R.id.oxygencard);
        Oxygen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                Date date=new Date(); // your date
// Choose time zone in which you want to interpret your Date
                Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
                cal.setTime(date);
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);
                currentdayofweek=cal.get(Calendar.DAY_OF_WEEK);
                if(day==1){
                    if(month==1|month==3|month==5|month==7|month==8|month==10|month==12)
                        day=32;
                    else
                        day=31;
                }
                String currentday= String.valueOf(7);
                if(day==1){
                    if(month==1|month==3|month==5|month==7|month==8|month==10|month==12)
                        day=32;
                    else
                        day=31;
                }
                String currentday1 = String.valueOf(day - 1);
                if(day==1){
                    if(month==1|month==3|month==5|month==7|month==8|month==10|month==12)
                        day=32;
                    else
                        day=31;
                }
                String currenday2 = String.valueOf(day - 2);
                if(day==1){
                    if(month==1|month==3|month==5|month==7|month==8|month==10|month==12)
                        day=32;
                    else
                        day=31;
                }
                String currentday3 = String.valueOf(day - 3);
                if(day==1){
                    if(month==1|month==3|month==5|month==7|month==8|month==10|month==12)
                        day=32;
                    else
                        day=31;
                }
                String currentday4 = String.valueOf(day - 4);
                if(day==1){
                    if(month==1|month==3|month==5|month==7|month==8|month==10|month==12)
                        day=32;
                    else
                        day=31;
                }
                String currentday5 = String.valueOf(day - 5);
                if(day==1){
                    if(month==1|month==3|month==5|month==7|month==8|month==10|month==12)
                        day=32;
                    else
                        day=31;
                }
                currentdayofweek=currentdayofweek-2;
                currentdayofweek%=7;
                String currenday6 = String.valueOf(day - 6);
                UserdetailsDB hb = new UserdetailsDB(getActivity());
                SQLiteDatabase db=hb.getReadableDatabase();
                ArrayList<BarData> dataList = new ArrayList<>();
                Cursor cur2 = db.rawQuery("SELECT  * FROM " + "hotwords" + " WHERE "+" day = ? OR day = ? OR day = ? OR day = ? OR day = ? OR day = ? OR day = ? LIMIT 7"   ,new String[] {currentday,currentday1,currenday2,currentday3,currentday4,currentday5,currenday6});
                while (cur2.moveToNext()){
                    String Oxygen=cur2.getString(cur2.getColumnIndex("Oxgenrate"));
                    BarData data = new BarData(daysofweek.get(currentdayofweek++), Float.parseFloat(Oxygen), Oxygen);
                    dataList.add(data);
                    currentdayofweek%=7;

                }



                graphdialog = new Dialog(getActivity());
                graphdialog.setContentView(R.layout.graphcard);


                final Button close = (Button) graphdialog.findViewById(R.id.close);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        graphdialog.dismiss();
                    }
                });









                ChartProgressBar mChart = (ChartProgressBar) graphdialog.findViewById(R.id.ChartProgressBar);
                mChart.setMaxValue(100);
                mChart.setDataList(dataList);
                mChart.build();

                mChart.setVisibility(View.VISIBLE);
                mChart.animate();


                graphdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                graphdialog.show();







            }
        });


        ImageButton previousdate = (ImageButton) view.findViewById(R.id.previousdate);
        previousdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                if(day==1){
                    if(month==1|month==3|month==5|month==7|month==8|month==10|month==12)
                    day=32;
                else {
                    if(month==2){
                        if(year%4==0)
                        day=30;
                        else
                            day=29;
                    }
                        else{
                            day=31;
                    }}
                }
                fragmentTransaction.replace(R.id.fragment_container, new DetailsFragment(day-1));
                fragmentTransaction.commit();
            }
        });


        final int currentday1 = cal.get(Calendar.DAY_OF_MONTH);

        ImageButton nextdate = (ImageButton) view.findViewById(R.id.nextdate);
        if(day>=currentday1){
            nextdate.setEnabled(false);
        }
        nextdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                if(day>=28){
                    if(month==1|month==3|month==5|month==7|month==8|month==10|month==12){
                        day%=32;
                    }
                    else {
                        if(month==2){
                            if(year%4==0)
                                day%=30;
                            else
                                day%=28;
                        }
                        else{
                            day%=31;
                        }}
                }
                fragmentTransaction.replace(R.id.fragment_container, new DetailsFragment(day+1));
                fragmentTransaction.commit();
            }
        });
        String strDate = Integer.toString(day)+"-";
        strDate+=dateFormat.format(date);

        TextView date1=(TextView) view.findViewById(R.id.date);

        date1.setText(strDate);

        ImageView heartinfo = (ImageView) view.findViewById(R.id.heartinfo);
        heartinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {





                infodialog = new Dialog(getActivity());
                infodialog.setContentView(R.layout.customdialog);
                TextView info =(TextView) infodialog.findViewById(R.id.info);
                info.setText("The heart rate is one of the ‘vital signs,’ or the important indicators of health in the human body. It measures the number of times that the heart contracts or beats per minute . The speed of the heartbeat varies as a result of physical activity, threats to safety, and emotional responses. The resting heart rate refers to the heart rate when a person is at rest.\n" +
                        "\n" +
                        "Healthy Range (Green) - Average 55-65 bpm\nBorderline Range (Orange) - Average 45-54 bpm & 66-75 bpm\n" +
                        "Unhealthy Range (Red) - Average <45 bpm & >75 bpm\n");

                Button button = (Button) infodialog.findViewById(R.id.close);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        infodialog.dismiss();
                    }
                });

                infodialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                infodialog.show();


            }
        });

        ImageView breathinfo = (ImageView) view.findViewById(R.id.Breathinfo);
        breathinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                infodialog = new Dialog(getActivity());
                infodialog.setContentView(R.layout.customdialog);
                TextView info =(TextView) infodialog.findViewById(R.id.info);
                info.setText("The Respiration Rate is another ‘Vital Sign’ which is one of the most important indicators of health in the human body. Respiration rate is reported in respirations (breaths) per minute. Each respiration has two phases: Inhalation and exhalation. During inhalation, oxygen is brought into the lungs from where it is transported throughout the body via the bloodstream. During exhalation, carbon dioxide is eliminated\nHealthy Range (Green) - Average 8-12 rpm\nBorderline Range (Orange) - Average 13-15 rpm\nUnhealthy Range (Red) - Average <8 rpm & >15 rpm"
);

                Button button = (Button) infodialog.findViewById(R.id.close);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        infodialog.dismiss();
                    }
                });

                infodialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                infodialog.show();




            }
        });


        ImageView oxygenlevel = (ImageView) view.findViewById(R.id.oxygeninfo);
        oxygenlevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                infodialog = new Dialog(getActivity());
                infodialog.setContentView(R.layout.customdialog);
                TextView info =(TextView) infodialog.findViewById(R.id.info);
                info.setText( "Oxygen saturation refers to the extent to which hemoglobin is saturated with oxygen. Hemoglobin is an element in your blood that binds with oxygen to carry it through the bloodstream to the organs, tissues, and cells of your body. It is an important parameter for managing patients in a clinical setup. \n" +
                        "\nHealthy Range (Green) - Average >94%\n" +
                        "Borderline Range (Orange) - Average 90-94%\n" +
                        "Unhealthy Range (Red) - Average <90%\n");

                Button button = (Button) infodialog.findViewById(R.id.close);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        infodialog.dismiss();
                    }
                });

                infodialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                infodialog.show();





            }
        });


        ImageView bp = (ImageView) view.findViewById(R.id.bpinfo);
        bp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                infodialog = new Dialog(getActivity());
                infodialog.setContentView(R.layout.customdialog);
                TextView info =(TextView) infodialog.findViewById(R.id.info);
                info.setText( "Blood pressure (BP) is the pressure of circulating blood against the walls of blood vessels. Most of this pressure results from the heart pumping blood through the circulatory system. When used without qualification, the term \"blood pressure\" refers to the pressure in the large arteries. Blood pressure is usually expressed in terms of the systolic pressure (maximum pressure during one heartbeat) over diastolic pressure(minimum pressure between two heartbeats) in the cardiac cycle. It is measured in millimeters of mercury (mmHg) above the surrounding atmospheric pressure.\n\n\n" +
                        "Healthy Range (Green)\nAverage Systolic: <130 mmHg \n Average Diastolic: <80 mmHg\n\nBorderline Range (Orange)\nAverage Systolic: 130-140 mmHg\nAverage Diastolic: 80-90 mmHg\n\nUnhealthy Range (Red)\nAverage Systolic: >140 mmHg\nAverage Diastolic: >90 mmHg");

                Button button = (Button) infodialog.findViewById(R.id.close);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        infodialog.dismiss();
                    }
                });

                infodialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                infodialog.show();


            }
        });

        ImageView sleepinfo = (ImageView) view.findViewById(R.id.sleepinfo);
        sleepinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                infodialog = new Dialog(getActivity());
                infodialog.setContentView(R.layout.customdialog);
                TextView info =(TextView) infodialog.findViewById(R.id.info);
                info.setText( "Sleep is a complex process of the body. Contrary to the popular belief that the brain and body shut down during sleep, both go through several processes to ensure adequate recovery — from burning calories to clearing neurotoxins and more. Most healthy adults need 7-9 hours of sleep as per NIH, USA. Dozee’s sleep score quantifies the efficacy of your sleep by measuring several parameters related to your sleep including body vitals, sleep routine, composition of sleep, and restfulness among others. \n" +
                        "\nHealthy Range (Green) - >80\n" +
                        "Borderline Range (Orange) - 70-79\n" +
                        "Unhealthy Range (Red) - <70\n");

                Button button = (Button) infodialog.findViewById(R.id.close);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        infodialog.dismiss();
                    }
                });

                infodialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                infodialog.show();

            }
        });

        ImageView recovery = (ImageView) view.findViewById(R.id.recoveryinfo);
        recovery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infodialog = new Dialog(getActivity());
                infodialog.setContentView(R.layout.customdialog);
                TextView info =(TextView) infodialog.findViewById(R.id.info);
                info.setText( "Stress is the body's response to anything that moves it away from being ‘Normal’ or ‘Healthy’. Stress hence is much more than the mental stress how we perceive it. It can be physical, hormonal, digestive, environmental, and mental. Recovery Score enables you to track the effectiveness of your sleep to help you recover from the stress that you accumulate during the day. High recovery levels are excellent indicators of good health.  \n" +
                        "\nHealthy Range (Green) - >90%\n" +
                        "Borderline Range (Orange) - 75-99%\n" +
                        "Unhealthy Range (Red) - <75%\n");

                Button button = (Button) infodialog.findViewById(R.id.close);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        infodialog.dismiss();
                    }
                });

                infodialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                infodialog.show();



            }
        });




        UserdetailsDB hb = new UserdetailsDB(getActivity());
        SQLiteDatabase db=hb.getReadableDatabase();
        String currentday=Integer.toString(day);
        Date date2=new Date(); // your date
// Choose time zone in which you want to interpret your Date
        Calendar cal3 = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        cal3.setTime(date2);
        int month = cal.get(Calendar.MONTH);
        currentmonth = String.valueOf(month);

        Cursor cur2 = db.rawQuery("SELECT  * FROM " + "hotwords" + " WHERE "+" day = ? AND month = ?"   ,new String[] {currentday,currentmonth});
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
        if(noofhearvalues!=0) {
            averageheartrate = totalheartrate / noofhearvalues;
        }
        if(noofbreathevalues!=0){
averagebreatherate=TotalBreatherate/noofbreathevalues;
        }
        if(noofoxygenvalues!=0){
         averageoxygenmeasure=oxygenmeasure/noofoxygenvalues;
        }
        if(noofsystolevalues!=0){
      averagesystole=TotalSystole/noofsystolevalues;
        }
        if(noofdiastolevalues!=0){
      averagediastole=TotalDiastole/noofdiastolevalues;
        }
        if(noofrecoveryvalues!=0){
      averagerecovery=TotalRecovery/noofrecoveryvalues;
        }
        if(noofsleepscores!=0) {
        averagesleep = TotalSleepscore / noofsleepscores;
        }



        TextView averagebreatherates = (TextView) view.findViewById(R.id.averagebreatherate);
        startCountAnimation(averagebreatherates,averagebreatherate,"\nBPM");

        CircularProgressBar circularProgressBar = (CircularProgressBar) view.findViewById(R.id.bpmmeasure);
        if(averagebreatherate>=8 & averagebreatherate<=12){
            circularProgressBar.setColor(ContextCompat.getColor(getActivity(), R.color.safe));
        }
        else if(averagebreatherate>=13 & averagebreatherate<=15)
        {
            circularProgressBar.setColor(ContextCompat.getColor(getActivity(), R.color.average));
        }
else{
                circularProgressBar.setColor(ContextCompat.getColor(getActivity(), R.color.danger));
            }

        circularProgressBar.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.gray));
        int animationDuration = 3000; // 2500ms = 2,5s
        circularProgressBar.setProgress(0);
        circularProgressBar.setProgressWithAnimation((averagebreatherate*100)/50, animationDuration);





       startCountAnimation(hearmeasure,averageheartrate,"\nBPM");
//        TextView heartrate = (TextView) getActivity().findViewById(R.id.heartmeasure);
//        heartrate.setText(averageheartrate);
        CircularProgressBar circularProgressBar1 = (CircularProgressBar) view.findViewById(R.id.heart);
        if(averageheartrate>=55 & averageheartrate<=65)
        {
            circularProgressBar1.setColor(ContextCompat.getColor(getActivity(), R.color.safe));
        }
     else if((averageheartrate>65 & averageheartrate<75)| (averageheartrate<=54 & averageheartrate>=45)){
            circularProgressBar1.setColor(ContextCompat.getColor(getActivity(), R.color.average));
        }
     else{
            circularProgressBar1.setColor(ContextCompat.getColor(getActivity(), R.color.danger));
        }
        circularProgressBar1.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.gray));
        int animationDuration1 = 3000; // 2500ms = 2,5s
        circularProgressBar1.setProgress(0);
        circularProgressBar1.setProgressWithAnimation((averageheartrate*100)/220, animationDuration1);


//
TextView Diastole= (TextView) view.findViewById(R.id.Diastole);
startCountAnimation(Diastole,averagediastole,"");
//Diastole.setText(averagediastole);
//
TextView Systole = (TextView) view.findViewById(R.id.Systole);
startCountAnimation(Systole,averagesystole,"");
//Systole.setText(averagesystole);
//
//TextView oxygenmeasurement = (TextView) getActivity().findViewById(R.id.oxygenmeasurement);
//oxygenmeasurement.setText(averageoxygenmeasure);
        TextView oxygenmeasurement = (TextView) view.findViewById(R.id.oxygenmeasurement);
        startCountAnimation(oxygenmeasurement,averageoxygenmeasure,"%");
        CircularProgressBar circularProgressBar5 = (CircularProgressBar) view.findViewById(R.id.oxygenmeasure);
        if(averageoxygenmeasure>94)
        {
            circularProgressBar5.setColor(ContextCompat.getColor(getActivity(), R.color.safe));
        }
        else if(averageoxygenmeasure<=94 & averageoxygenmeasure>=90){
            circularProgressBar5.setColor(ContextCompat.getColor(getActivity(), R.color.average));

        }
else{
            circularProgressBar5.setColor(ContextCompat.getColor(getActivity(), R.color.danger));
        }
        circularProgressBar5.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.gray));
        int animationDuration5 = 3000; // 2500ms = 2,5s
        circularProgressBar5.setProgress(0);
        circularProgressBar5.setProgressWithAnimation(averageoxygenmeasure, animationDuration5);






        CardView sleep = (CardView) view.findViewById(R.id.sleepcard);
        sleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Date date=new Date(); // your date
// Choose time zone in which you want to interpret your Date
                Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
                cal.setTime(date);
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);
                currentdayofweek=cal.get(Calendar.DAY_OF_WEEK);
                if(day==1){
                    if(month==1|month==3|month==5|month==7|month==8|month==10|month==12)
                        day=32;
                    else
                        day=31;
                }
                String currentday= String.valueOf(7);
                if(day==1){
                    if(month==1|month==3|month==5|month==7|month==8|month==10|month==12)
                        day=32;
                    else
                        day=31;
                }
                String currentday1 = String.valueOf(day - 1);
                if(day==1){
                    if(month==1|month==3|month==5|month==7|month==8|month==10|month==12)
                        day=32;
                    else
                        day=31;
                }
                String currenday2 = String.valueOf(day - 2);
                if(day==1){
                    if(month==1|month==3|month==5|month==7|month==8|month==10|month==12)
                        day=32;
                    else
                        day=31;
                }
                String currentday3 = String.valueOf(day - 3);
                if(day==1){
                    if(month==1|month==3|month==5|month==7|month==8|month==10|month==12)
                        day=32;
                    else
                        day=31;
                }
                String currentday4 = String.valueOf(day - 4);
                if(day==1){
                    if(month==1|month==3|month==5|month==7|month==8|month==10|month==12)
                        day=32;
                    else
                        day=31;
                }
                String currentday5 = String.valueOf(day - 5);
                if(day==1){
                    if(month==1|month==3|month==5|month==7|month==8|month==10|month==12)
                        day=32;
                    else
                        day=31;
                }
                currentdayofweek=currentdayofweek-2;
                currentdayofweek%=7;
                String currenday6 = String.valueOf(day - 6);
                UserdetailsDB hb = new UserdetailsDB(getActivity());
                SQLiteDatabase db=hb.getReadableDatabase();
                ArrayList<BarData> dataList = new ArrayList<>();
                Cursor cur2 = db.rawQuery("SELECT  * FROM " + "hotwords" + " WHERE "+" day = ? OR day = ? OR day = ? OR day = ? OR day = ? OR day = ? OR day = ? LIMIT 7"   ,new String[] {currentday,currentday1,currenday2,currentday3,currentday4,currentday5,currenday6});
                while (cur2.moveToNext()){
                    String sleeps=cur2.getString(cur2.getColumnIndex("Sleepscore"));
                    BarData data = new BarData(daysofweek.get(currentdayofweek++), Float.parseFloat(sleeps), sleeps);
                    dataList.add(data);
                    currentdayofweek%=7;

                }



                graphdialog = new Dialog(getActivity());
                graphdialog.setContentView(R.layout.graphcard);


                final Button close = (Button) graphdialog.findViewById(R.id.close);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        graphdialog.dismiss();
                    }
                });









                ChartProgressBar mChart = (ChartProgressBar) graphdialog.findViewById(R.id.ChartProgressBar);
                mChart.setMaxValue(100);
                mChart.setDataList(dataList);
                mChart.build();

                mChart.setVisibility(View.VISIBLE);
                mChart.animate();


                graphdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                graphdialog.show();








            }
        });



//        TextView sleepmeasurement = (TextView) getActivity().findViewById(R.id.sleepmeasurementtext);
//        sleepmeasurement.setText(averagesleep);
        TextView sleepmeasurement = (TextView) view.findViewById(R.id.sleepmeasurementtext);
        startCountAnimation(sleepmeasurement,averagesleep,"%");
        CircularProgressBar circularProgressBar3 = (CircularProgressBar) view.findViewById(R.id.sleepmeasurement);
        if(averagesleep>80){
            circularProgressBar3.setColor(ContextCompat.getColor(getActivity(), R.color.safe));
        }
        else if(averagesleep>=70 & averagesleep<80){
            circularProgressBar3.setColor(ContextCompat.getColor(getActivity(), R.color.average));
        }
        else{
            circularProgressBar3.setColor(ContextCompat.getColor(getActivity(), R.color.danger));
        }

        circularProgressBar3.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.gray));
        int animationDuration3 = 3000; // 2500ms = 2,5s
        circularProgressBar3.setProgress(0);
        circularProgressBar3.setProgressWithAnimation(averagesleep, animationDuration3);




//        TextView brainrecovery = (TextView) getActivity().findViewById(R.id.brainrecovery) ;
//        brainrecovery.setText(averagerecovery);



        CardView recoverycard = (CardView) view.findViewById(R.id.recoverycard);
        recoverycard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Date date=new Date(); // your date
// Choose time zone in which you want to interpret your Date
                Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
                cal.setTime(date);
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);
                currentdayofweek=cal.get(Calendar.DAY_OF_WEEK);
                if(day==1){
                    if(month==1|month==3|month==5|month==7|month==8|month==10|month==12)
                        day=32;
                    else
                        day=31;
                }
                String currentday= String.valueOf(7);
                if(day==1){
                    if(month==1|month==3|month==5|month==7|month==8|month==10|month==12)
                        day=32;
                    else
                        day=31;
                }
                String currentday1 = String.valueOf(day - 1);
                if(day==1){
                    if(month==1|month==3|month==5|month==7|month==8|month==10|month==12)
                        day=32;
                    else
                        day=31;
                }
                String currenday2 = String.valueOf(day - 2);
                if(day==1){
                    if(month==1|month==3|month==5|month==7|month==8|month==10|month==12)
                        day=32;
                    else
                        day=31;
                }
                String currentday3 = String.valueOf(day - 3);
                if(day==1){
                    if(month==1|month==3|month==5|month==7|month==8|month==10|month==12)
                        day=32;
                    else
                        day=31;
                }
                String currentday4 = String.valueOf(day - 4);
                if(day==1){
                    if(month==1|month==3|month==5|month==7|month==8|month==10|month==12)
                        day=32;
                    else
                        day=31;
                }
                String currentday5 = String.valueOf(day - 5);
                if(day==1){
                    if(month==1|month==3|month==5|month==7|month==8|month==10|month==12)
                        day=32;
                    else
                        day=31;
                }
                currentdayofweek=currentdayofweek-2;
                currentdayofweek%=7;
                String currenday6 = String.valueOf(day - 6);
                UserdetailsDB hb = new UserdetailsDB(getActivity());
                SQLiteDatabase db=hb.getReadableDatabase();
                ArrayList<BarData> dataList = new ArrayList<>();
                Cursor cur2 = db.rawQuery("SELECT  * FROM " + "hotwords" + " WHERE "+" day = ? OR day = ? OR day = ? OR day = ? OR day = ? OR day = ? OR day = ? LIMIT 7"   ,new String[] {currentday,currentday1,currenday2,currentday3,currentday4,currentday5,currenday6});
                while (cur2.moveToNext()){
                    String recoverys=cur2.getString(cur2.getColumnIndex("Recovery"));
                    BarData data = new BarData(daysofweek.get(currentdayofweek++), Float.parseFloat(recoverys), recoverys);
                    dataList.add(data);
                    currentdayofweek%=7;

                }



                graphdialog = new Dialog(getActivity());
                graphdialog.setContentView(R.layout.graphcard);


                final Button close = (Button) graphdialog.findViewById(R.id.close);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        graphdialog.dismiss();
                    }
                });








                ChartProgressBar mChart = (ChartProgressBar) graphdialog.findViewById(R.id.ChartProgressBar);
                mChart.setMaxValue(100);
                mChart.setDataList(dataList);
                mChart.build();

                mChart.setVisibility(View.VISIBLE);
                mChart.animate();


                graphdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                graphdialog.show();








            }
        });


        CardView bpcards = (CardView) view.findViewById(R.id.bpcards);
        bpcards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Date date = new Date(); // your date
// Choose time zone in which you want to interpret your Date
                Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
                cal.setTime(date);
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                currentdayofweek = cal.get(Calendar.DAY_OF_WEEK);
                if (day == 1) {
                    if (month == 1 | month == 3 | month == 5 | month == 7 | month == 8 | month == 10 | month == 12)
                        day = 32;
                    else
                        day = 31;
                }
                String currentday = String.valueOf(7);
                if (day == 1) {
                    if (month == 1 | month == 3 | month == 5 | month == 7 | month == 8 | month == 10 | month == 12)
                        day = 32;
                    else
                        day = 31;
                }
                String currentday1 = String.valueOf(day - 1);
                if (day == 1) {
                    if (month == 1 | month == 3 | month == 5 | month == 7 | month == 8 | month == 10 | month == 12)
                        day = 32;
                    else
                        day = 31;
                }
                String currenday2 = String.valueOf(day - 2);
                if (day == 1) {
                    if (month == 1 | month == 3 | month == 5 | month == 7 | month == 8 | month == 10 | month == 12)
                        day = 32;
                    else
                        day = 31;
                }
                String currentday3 = String.valueOf(day - 3);
                if (day == 1) {
                    if (month == 1 | month == 3 | month == 5 | month == 7 | month == 8 | month == 10 | month == 12)
                        day = 32;
                    else
                        day = 31;
                }
                String currentday4 = String.valueOf(day - 4);
                if (day == 1) {
                    if (month == 1 | month == 3 | month == 5 | month == 7 | month == 8 | month == 10 | month == 12)
                        day = 32;
                    else
                        day = 31;
                }
                String currentday5 = String.valueOf(day - 5);
                if (day == 1) {
                    if (month == 1 | month == 3 | month == 5 | month == 7 | month == 8 | month == 10 | month == 12)
                        day = 32;
                    else
                        day = 31;
                }
                currentdayofweek = currentdayofweek - 2;
                currentdayofweek %= 7;
                String currenday6 = String.valueOf(day - 6);
                UserdetailsDB hb = new UserdetailsDB(getActivity());
                SQLiteDatabase db = hb.getReadableDatabase();
                ArrayList<BarData> dataList = new ArrayList<>();
                Cursor cur2 = db.rawQuery("SELECT  * FROM " + "hotwords" + " WHERE " + " day = ? OR day = ? OR day = ? OR day = ? OR day = ? OR day = ? OR day = ? LIMIT 7", new String[]{currentday, currentday1, currenday2, currentday3, currentday4, currentday5, currenday6});
                while (cur2.moveToNext()) {
                    String Systolevalue = cur2.getString(cur2.getColumnIndex("Systole"));
                    String Diastolevalue = cur2.getString(cur2.getColumnIndex("Diastole"));
                    BarData data = new BarData("S", Float.parseFloat(Systolevalue), Systolevalue);
                    dataList.add(data);
                    BarData data1 = new BarData("D", Float.parseFloat(Diastolevalue), Diastolevalue);
                    dataList.add(data1);
                    currentdayofweek %= 7;

                }


                graphdialog = new Dialog(getActivity());
                graphdialog.setContentView(R.layout.graphcard);


                final Button close = (Button) graphdialog.findViewById(R.id.close);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        graphdialog.dismiss();


                    }
                });


                ChartProgressBar mChart = (ChartProgressBar) graphdialog.findViewById(R.id.ChartProgressBar);
                mChart.setMaxValue(100);
                mChart.setDataList(dataList);
                mChart.build();

                mChart.setVisibility(View.VISIBLE);
                mChart.animate();


                graphdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                graphdialog.show();




            }
        });


        TextView brainrecovery = (TextView) view.findViewById(R.id.brainrecovery) ;
        startCountAnimation(brainrecovery,averagerecovery,"%");
        CircularProgressBar circularProgressBar4 = (CircularProgressBar) view.findViewById(R.id.brainmeasurement);
        if(averagerecovery>90){
            circularProgressBar4.setColor(ContextCompat.getColor(getActivity(), R.color.safe));
        }
        else if(averagerecovery>75 & averagerecovery<=90){
            circularProgressBar4.setColor(ContextCompat.getColor(getActivity(), R.color.average));
        }
        else{
            circularProgressBar4.setColor(ContextCompat.getColor(getActivity(), R.color.danger));
        }
        circularProgressBar4.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.gray));
        int animationDuration4 = 5000; // 2500ms = 2,5s
        circularProgressBar4.setProgress(0);
        circularProgressBar4.setProgressWithAnimation(averagerecovery, animationDuration4);

        Resources res = getResources();
        Drawable redup = res.getDrawable(R.drawable.redup);
        Drawable reddown = res.getDrawable(R.drawable.reddown);
        Drawable greenup = res.getDrawable(R.drawable.greenup);
        Drawable greendown = res.getDrawable(R.drawable.greendown_24dp);
        Drawable orangeup = res.getDrawable(R.drawable.orangeup);
        Drawable orangedown = res.getDrawable(R.drawable.orangedown);
ImageView bpup = (ImageView) view.findViewById(R.id.bpup);
ImageView bpdown = (ImageView) view.findViewById(R.id.bpdown);
if(averagesystole<135 & averagediastole<80){
bpup.setImageDrawable(greenup);
bpdown.setImageDrawable(greendown);

}
else if((averagesystole>130 & averagesystole<140) | (averagediastole>80 & averagediastole<90)){

    bpup.setImageDrawable(orangeup);
    bpdown.setImageDrawable(orangedown);

}
else{

    bpup.setImageDrawable(redup);
    bpdown.setImageDrawable(reddown);
}




        return view;
    }

    private void startCountAnimation(final TextView tv, final int value, final String unit) {
        ValueAnimator animator = ValueAnimator.ofInt(0, value);
        animator.setDuration(3000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                if(value==0){
                    tv.setText("NA");
                }
                else{
                    tv.setText(animation.getAnimatedValue().toString()+unit);
                }}
        });
        animator.start();
    }

}
