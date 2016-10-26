package com.simpalm.meetingtracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Simpalm on 9/30/16.
 */

public class EventDataSource {
    SqliteOpenHelper sqliteHelper;
    SQLiteDatabase sqLiteDatabase;
    Context mContext;


    public EventDataSource(Context context) {

        sqliteHelper = new SqliteOpenHelper(context);
        mContext = context;


    }

    public void open() {

        sqLiteDatabase = sqliteHelper.getWritableDatabase();


    }

    public long insertNewEvent(String eventTitle, String eventDesc, String eventDateTime, String eventLocation) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SqliteOpenHelper.EVENT_TITLE, eventTitle);
        contentValues.put(SqliteOpenHelper.EVENT_DESCRIPTION, eventDesc);
        contentValues.put(SqliteOpenHelper.EVENT_DATE_TIME, eventDateTime);
        contentValues.put(SqliteOpenHelper.EVENT_LOCATION, eventLocation);
        long rowInserted = sqLiteDatabase.insert(SqliteOpenHelper.EVENT_TABLE, null, contentValues);

        if (rowInserted != -1) {


            Log.d("NEW EVENT DATA", "inserted data");

        }
        return rowInserted;
    }

    public void closeDatabase() {

        sqLiteDatabase.close();
    }
}


// select table where user_name =