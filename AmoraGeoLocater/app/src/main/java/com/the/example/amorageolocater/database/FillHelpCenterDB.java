package com.the.example.amorageolocater.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by what on 7/30/2017.
 */


public class FillHelpCenterDB {

    private SQLiteDatabase sqLiteDatabase;
    private CreateHelpCenterDB createHelpCenterDB;


     public FillHelpCenterDB(Context context) {
          createHelpCenterDB = new CreateHelpCenterDB(context);
          sqLiteDatabase= createHelpCenterDB.getWritableDatabase();
         List<HelpCenterDataDb> helpCenters = new ArrayList<>();
         helpCenters.add(new HelpCenterDataDb(13,39,"0938179823","http://192.168.4.89/Req/"));
         helpCenters.add(new HelpCenterDataDb(23,49,"0938179823","http://192.168.4.89/Req/"));
         helpCenters.add(new HelpCenterDataDb(33,59,"0938179823","http://192.168.4.89/Req/"));
         helpCenters.add(new HelpCenterDataDb(43,69,"0938179823","http://192.168.4.89/Req/"));
         helpCenters.add(new HelpCenterDataDb(53,79,"0938179823","http://192.168.4.89/Req/"));
         helpCenters.add(new HelpCenterDataDb(63,89,"0938179823","http://192.168.4.89/Req/"));
         helpCenters.add(new HelpCenterDataDb(73,99,"0938179823","http://192.168.4.89/Req/"));
         helpCenters.add(new HelpCenterDataDb(83,109,"0938179823","http://192.168.4.89/Req/"));
         for (HelpCenterDataDb data:helpCenters)
         {
             createNewDb(data);
         }
    }
    private void createNewDb(HelpCenterDataDb helpCenterDataDb)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(HelpCenterDbSchema.HelpCenterTable.HelpCenterColumns.UUID, helpCenterDataDb.getUUid().toString());
        contentValues.put(HelpCenterDbSchema.HelpCenterTable.HelpCenterColumns.PHONE_NUMBER, helpCenterDataDb.getPhoneNumber());
        contentValues.put(HelpCenterDbSchema.HelpCenterTable.HelpCenterColumns.URL_PATH, helpCenterDataDb.getUrlPath());
        contentValues.put(HelpCenterDbSchema.HelpCenterTable.HelpCenterColumns.LATITUDE, helpCenterDataDb.getLatitude());
        contentValues.put(HelpCenterDbSchema.HelpCenterTable.HelpCenterColumns.LONGITUDE, helpCenterDataDb.getLongitude());
        long result=sqLiteDatabase.insert(HelpCenterDbSchema.HelpCenterTable.TABLE_Name,null,contentValues);


    }

    public List<HelpCenterDataDb> queryHelpCenters()
    {
        List<HelpCenterDataDb> list= new ArrayList<>();
        sqLiteDatabase=createHelpCenterDB.getReadableDatabase();
        String[] columns={HelpCenterDbSchema.HelpCenterTable.HelpCenterColumns.UUID,
                          HelpCenterDbSchema.HelpCenterTable.HelpCenterColumns.PHONE_NUMBER,
                          HelpCenterDbSchema.HelpCenterTable.HelpCenterColumns.URL_PATH,
                          HelpCenterDbSchema.HelpCenterTable.HelpCenterColumns.LATITUDE,
                          HelpCenterDbSchema.HelpCenterTable.HelpCenterColumns.LONGITUDE};
        Cursor cursor=sqLiteDatabase.query(HelpCenterDbSchema.HelpCenterTable.TABLE_Name,columns ,null,null,null,null,null);
        try {

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String uuid = cursor.getString(0);
                String phoneNumber = cursor.getString(1);
                String urlPath=cursor.getString(2);
                double latitude = cursor.getDouble(3);
                double longitude = cursor.getDouble(4);
                HelpCenterDataDb helpCenterData = new HelpCenterDataDb(latitude, longitude, phoneNumber,urlPath);
                list.add(helpCenterData);
                cursor.moveToNext();
            }
        }
            finally{
                cursor.close();
            }

            return list;



    }


}
