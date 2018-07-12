package com.the.example.amorageolocater.database;

/**
 * Created by what on 7/30/2017.
 */

public class HelpCenterDbSchema {
    public static class HelpCenterTable{
        public static String TABLE_Name="helpcenters";
        public static class HelpCenterColumns{
            public static String UUID="uuid";
            public static String PHONE_NUMBER="phone";
            public static String URL_PATH="uri";
            public static String LATITUDE="latitude";
            public static String LONGITUDE="longitude";

        }


    }
}
