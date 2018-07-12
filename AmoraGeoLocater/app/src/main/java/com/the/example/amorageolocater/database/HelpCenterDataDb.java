package com.the.example.amorageolocater.database;

import java.util.UUID;

/**
 * Created by what on 7/30/2017.
 */

public class HelpCenterDataDb {

    private UUID UUid;
    private double latitude;
    private double longitude;
    private String phoneNumber;
    private String urlPath;

     HelpCenterDataDb(double latitude, double longitude, String phoneNumber, String urlPath)
    {

        setUUid(UUID.randomUUID());
        setLatitude(latitude);
        setLongitude(longitude);
        setPhoneNumber(phoneNumber);
        setUrlPath(urlPath);
    }
    public UUID getUUid() {
        return UUid;
    }

    public void setUUid(UUID UUid) {
        this.UUid = UUid;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }
}
