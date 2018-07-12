package com.the.example.amorageolocater.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import  com.the.example.amorageolocater.database.HelpCenterDbSchema.HelpCenterTable;

/**
 * Created by what on 7/30/2017.
 */

public class CreateHelpCenterDB extends SQLiteOpenHelper{
    private final static String databaseName="HelpCenter.db";
    private final static int database_version=1;

    private String createTable="CREATE TABLE "+HelpCenterTable.TABLE_Name+"("+
            HelpCenterTable.HelpCenterColumns.UUID+ " TEXT PRIMARY KEY ,"+
            HelpCenterTable.HelpCenterColumns.PHONE_NUMBER+" TEXT ,"+
            HelpCenterTable.HelpCenterColumns.URL_PATH+" TEXT ," +
            HelpCenterTable.HelpCenterColumns.LATITUDE +" DOUBLE ,"+
            HelpCenterTable.HelpCenterColumns.LONGITUDE+" DOUBLE "+
            ");" ;

    public CreateHelpCenterDB(Context context) {
        super(context, databaseName, null, database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exist"+HelpCenterTable.TABLE_Name);
        onCreate(db);

    }
}
