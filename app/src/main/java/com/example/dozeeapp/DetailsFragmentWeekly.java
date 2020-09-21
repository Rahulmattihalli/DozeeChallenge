package com.example.dozeeapp;

import android.animation.ValueAnimator;
import android.app.Dialog;
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

import com.hadiidbouk.charts.BarData;
import com.hadiidbouk.charts.ChartProgressBar;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class DetailsFragmentWeekly extends Fragment {
Dialog infodialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.framelayout1,container,false);



        TextView date1=(TextView) view.findViewById(R.id.date);
        date1.setText("Last 7 days data");
        ImageButton nextdate = (ImageButton) view.findViewById(R.id.nextdate);
        nextdate.setVisibility(View.INVISIBLE);
        ImageButton previousdate = (ImageButton) view.findViewById(R.id.previousdate);
        previousdate.setVisibility(View.INVISIBLE);

        ImageView heartinfo = (ImageView) view.findViewById(R.id.heartinfo);
        heartinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                infodialog = new Dialog(getActivity());
                infodialog.setContentView(R.layout.customdialog);
                TextView info =(TextView) infodialog.findViewById(R.id.info);
//                info.setVisibility(View.INVISIBLE);
                info.setText("The heart rate is one of the ‘vital signs,’ or the important indicators of health in the human body. It measures the number of times that the heart contracts or beats per minute . The speed of the heartbeat varies as a result of physical activity, threats to safety, and emotional responses. The resting heart rate refers to the heart rate when a person is at rest.\n" +
                        "\n" +
                        "Healthy Range (Green) - Average 55-65 bpm\nBorderline Range (Orange) - Average 45-54 bpm & 66-75 bpm\n" +
                        "Unhealthy Range (Red) - Average <45 bpm & >75 bpm\n");



//                ArrayList<BarData> dataList = new ArrayList<>();
//
//                BarData data = new BarData("Sep", 3.4f, "3.4€");
//                dataList.add(data);
//
//                data = new BarData("Oct", 8.0f, "8.0€");
//                dataList.add(data);
//
//                data = new BarData("Nov", 1.8f, "1.8€");
//                dataList.add(data);
//
//                data = new BarData("Dec", 7.3f, "7.3€");
//                dataList.add(data);
//
//                data = new BarData("Jan", 6.2f, "6.2€");
//                dataList.add(data);
//
//                data = new BarData("Feb", 3.3f, "3.3€");
//                dataList.add(data);
//
//                ChartProgressBar mChart = (ChartProgressBar) infodialog.findViewById(R.id.ChartProgressBar);
//                mChart.setVisibility(View.VISIBLE);
//                mChart.setDataList(dataList);
//                mChart.build();
//                mChart.disableBar(dataList.size() - 1);
//



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



        Date date=new Date(); // your date
// Choose time zone in which you want to interpret your Date
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day=cal.get(Calendar.DAY_OF_MONTH);
        String currentday= String.valueOf(10);
            String currentday1 = String.valueOf((day - 1));
            String currenday2 = String.valueOf((day - 2));
            String currentday3 = String.valueOf((day - 3));
            String currentday4 = String.valueOf((day - 4));
            String currentday5 = String.valueOf((day - 5));
            String currenday6 = String.valueOf((day - 6));

        UserdetailsDB hb = new UserdetailsDB(getActivity());
        SQLiteDatabase db=hb.getReadableDatabase();

        Cursor cur2 = db.rawQuery("SELECT  * FROM " + "hotwords" + " WHERE "+" day = ? OR day = ? OR day = ? OR day = ? OR day = ? OR day = ? OR day = ?"   ,new String[] {currentday,currentday1,currenday2,currentday3,currentday4,currentday5,currenday6});
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
        if(averageheartrate==0|averagebreatherate==0|averagediastole==0|averagesystole==0|averagerecovery==0|averagesleep==0){

        }



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

        TextView averagebreatherates = (TextView) view.findViewById(R.id.averagebreatherate);
        startCountAnimation(averagebreatherates,averagebreatherate,"\nBPM");


       TextView hearmeasure = view.findViewById(R.id.heartmeasure);
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


        TextView Diastole= (TextView) view.findViewById(R.id.Diastole);
        startCountAnimation(Diastole,averagediastole,"");
//Diastole.setText(averagediastole);
//
        TextView Systole = (TextView) view.findViewById(R.id.Systole);
        startCountAnimation(Systole,averagesystole,"");

//
//TextView Diastole= (TextView) getActivity().findViewById(R.id.Diastole);
//Diastole.setText(averagediastole);
//
//TextView Systole = (TextView) getActivity().findViewById(R.id.Systole);
//Systole.setText(averagesystole);
//
//TextView oxygenmeasurement = (TextView) getActivity().findViewById(R.id.oxygenmeasurement);

        TextView oxygenmeasurement = (TextView) view.findViewById(R.id.oxygenmeasurement);
        startCountAnimation(oxygenmeasurement,averageoxygenmeasure,"%");
//oxygenmeasurement.setText(averageoxygenmeasure);
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



        TextView sleepmeasurement = (TextView) view.findViewById(R.id.sleepmeasurementtext);
        startCountAnimation(sleepmeasurement,averagesleep,"%");

//        TextView sleepmeasurement = (TextView) getActivity().findViewById(R.id.sleepmeasurementtext);
//        sleepmeasurement.setText(averagesleep);
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



        TextView brainrecovery = (TextView) view.findViewById(R.id.brainrecovery) ;
        startCountAnimation(brainrecovery,averagerecovery,"%");

//        TextView brainrecovery = (TextView) getActivity().findViewById(R.id.brainrecovery) ;
//        brainrecovery.setText(averagerecovery);
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
