package com.the.example.amorageolocater;

import android.graphics.Bitmap;

import java.util.UUID;

/**
 * Created by what on 7/14/2017.
 */

public class AccidentDatas {


    private int imageURL;
    private String title;
    private String subTitle;
    private UUID helpCenterId;


    public AccidentDatas() {
        this.helpCenterId=UUID.randomUUID();
    }


   public int getImageURL() {
        return imageURL;
    }

    public void setImageURL(int imageURL) {
        this.imageURL = imageURL;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }


     public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void setHelpCenterId(UUID helpCenterId) {
        this.helpCenterId = helpCenterId;
    }

    public UUID getHelpCenterId() {
        return helpCenterId;
    }

     public int getUrl() {

        return imageURL;
    }



}
