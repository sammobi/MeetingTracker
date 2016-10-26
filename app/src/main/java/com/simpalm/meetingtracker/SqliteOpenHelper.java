package com.simpalm.meetingtracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Simpalm on 9/30/16.
 */

public class SqliteOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "EventDatabase.db";
    private static final int DATABASE_VERSION = 1;
    public static final String EVENT_TABLE = "EventTable";
    public static final String EVENT_TITLE = "EventTitle";
    public static final String EVENT_DESCRIPTION = "EventDescription";
    public static final String EVENT_DATE_TIME = "EventDateTime";
    public static final String EVENT_LOCATION = "EventLocation";
    public static final double EVENT_LAT = 0;
    public static final double EVENT_LONG = 0;
    public static final String COLUMN_NAME_ID = "_id";

    private static final String DATABASE_EVENT = "create table " + EVENT_TABLE + " (" + COLUMN_NAME_ID + " integer primary key autoincrement," +
            EVENT_TITLE + " text not null, " + EVENT_DESCRIPTION + " text not null, " + EVENT_DATE_TIME + " text not null, " + EVENT_LOCATION + " text not null);";


    private static final String DATABASE_DELETE_QUERY = "drop table if exists " + EVENT_TABLE;


    public SqliteOpenHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);


    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_EVENT);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DATABASE_DELETE_QUERY);
        sqLiteDatabase.execSQL(EVENT_TABLE);
        onCreate(sqLiteDatabase);
    }

}
