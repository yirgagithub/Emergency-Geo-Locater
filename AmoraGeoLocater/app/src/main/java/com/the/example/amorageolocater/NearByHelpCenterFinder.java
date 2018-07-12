package com.the.example.amorageolocater;

import android.content.Context;

import com.the.example.amorageolocater.database.FillHelpCenterDB;
import com.the.example.amorageolocater.database.HelpCenterDataDb;

import java.util.List;

/**
 * Created by what on 8/1/2017.
 */

public  class NearByHelpCenterFinder {



    public static String[] getNearByHelpCenter(Context context, double latitude, double longitude)
    {
        FillHelpCenterDB fillHelpCenterDB= new FillHelpCenterDB(context);
        List<HelpCenterDataDb> listHelpCenters=fillHelpCenterDB.queryHelpCenters();

        String[] targetedHelpCenter=new String[]{listHelpCenters.get(0).getPhoneNumber(), listHelpCenters.get(0).getUrlPath()};
        double minLatitude=Math.abs(listHelpCenters.get(0).getLatitude()-latitude);
        double minLongitude=Math.abs(listHelpCenters.get(0).getLongitude()-longitude);
        for(HelpCenterDataDb helpCenterDataDb: listHelpCenters)
        {
            double databaseLatitude=helpCenterDataDb.getLatitude();
            double databaseLongitude=helpCenterDataDb.getLongitude();
            boolean isNear=Math.abs(databaseLatitude-latitude)<minLatitude && Math.abs(databaseLongitude-longitude)<minLongitude;
            if(isNear)
                targetedHelpCenter= new String[]{helpCenterDataDb.getPhoneNumber(),helpCenterDataDb.getUrlPath()};

        }

        return targetedHelpCenter;
    }
}
