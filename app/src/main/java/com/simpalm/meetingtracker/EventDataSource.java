package com.simpalm.meetingtracker;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Simpalm on 9/30/16.
 */

public class EventDataSource {
    SqliteOpenHelper sqliteHelper;
    SQLiteDatabase sqLiteDatabase;
    public SharedPreferences mSharedPreferences;
    Context mContext;

    String[] columnNames = {SqliteOpenHelper.COLUMN_NAME_ID, SqliteOpenHelper.EVENT_TITLE, SqliteOpenHelper.EVENT_DESCRIPTION, SqliteOpenHelper.EVENT_DATE_TIME, SqliteOpenHelper.EVENT_LOCATION};


    public EventDataSource(Context context) {

        sqliteHelper = new SqliteOpenHelper(context);
        mContext = context;


    }

    public void open() {

        sqLiteDatabase = sqliteHelper.getWritableDatabase();


    }

    public void insertNewEvent(String eventTitle, String eventDesc, String eventDateTime, String eventLocation) {

        ContentValues contentValues = new ContentValues();

        contentValues.put(SqliteOpenHelper.EVENT_TITLE, eventTitle);
        contentValues.put(SqliteOpenHelper.EVENT_DESCRIPTION, eventDesc);
        contentValues.put(SqliteOpenHelper.EVENT_DATE_TIME, eventDateTime);
        contentValues.put(SqliteOpenHelper.EVENT_LOCATION, eventLocation);
        sqLiteDatabase.insert(SqliteOpenHelper.EVENT_TABLE, null, contentValues);
        Log.d("NEW EVENT DATA", "inserted data");


    }

    public void closeDatabase() {

        sqLiteDatabase.close();
    }
}


// select table where user_name =