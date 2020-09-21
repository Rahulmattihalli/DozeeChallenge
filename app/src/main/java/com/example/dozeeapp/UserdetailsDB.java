package com.example.dozeeapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UserdetailsDB extends SQLiteOpenHelper {
    private static final int DB_VERSION = 3;
    public static final String DB_NAME = "User_db";
    private static final String TABLE_Users = "hotwords";
    private static final String KEY_ID = "id";
    private static final String KEY_DAY = "day";
    private static final String KEY_MONTH="month";
    private static final String KEY_YEAR="Year";
    private static final String KEY_HEARTRATE = "Heartrate";
    private static final String KEY_BREATHERATE = "Breatherate";
    private static final String KEY_OXYGEN = "Oxgenrate";
    private static final String KEY_SYSTOLE = "Systole";
    private static final String KEY_DIASTOLE = "Diastole";
    private static final String KEY_RECOVERY = "Recovery";
    private static final String KEY_SLEEPSCORE = "Sleepscore";


    public UserdetailsDB(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(
                "create table hotwords " +
                        "(id integer primary key, day integer, month integer, Year integer, Heartrate text,Breatherate text, Oxgenrate text, Systole text,Diastole text,Recovery text, Sleepscore text)"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Users);
        // Create tables again
        onCreate(db);
    }
    // **** CRUD (Create, Read, Update, Delete) Operations ***** //

    // Adding new User Details
    public void insertWordDetails(int id,int day,int month, int year,String Heartrate, String Breatherate, String Oxgenrate, String Systole, String Diastole, String Recovery, String Sleepscore){
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_ID, id);
        cValues.put(KEY_DAY, day);
        cValues.put(KEY_MONTH, month);
        cValues.put(KEY_YEAR, year);
        cValues.put(KEY_HEARTRATE, Heartrate);
        cValues.put(KEY_BREATHERATE, Breatherate);
        cValues.put(KEY_OXYGEN, Oxgenrate);
        cValues.put(KEY_SYSTOLE,Systole);
        cValues.put(KEY_DIASTOLE,Diastole);
        cValues.put(KEY_RECOVERY,Recovery);
        cValues.put(KEY_SLEEPSCORE,Sleepscore);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_Users,null, cValues);
        Log.d("ROWid:",""+newRowId);
        db.close();
    }
    // Get User Details

    // Get User Details based on userid
   }