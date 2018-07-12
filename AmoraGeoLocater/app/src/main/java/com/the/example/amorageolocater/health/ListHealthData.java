package com.the.example.amorageolocater.health;

import android.content.Context;

import com.the.example.amorageolocater.AccidentDatas;
import com.the.example.amorageolocater.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by what on 7/14/2017.
 */

public class ListHealthData {

    private static ListHealthData mListHealthData;
    private static List<AccidentDatas> mHelpCentersData;
    static ListHealthData get(Context context)
    {

        if(mListHealthData==null)
            mListHealthData =new ListHealthData(context);
        return mListHealthData;
    }
    private ListHealthData(Context context)
    {
        int index;
        int imageURL=0;
        String[] titleArray=context.getResources().getStringArray(R.array.health_title);
        String title="";
        String subTitle="";
        mHelpCentersData=new ArrayList<>();
        for(index=0;index<10;index++)
        {
         AccidentDatas mAccidentDatas = new AccidentDatas();
            switch (index+1)
            {
                case 1:
                    title=titleArray[0];
                    subTitle="Car Accident Car Accident Car Accident";
                    imageURL= R.drawable.health6;
                    break;
                case 2:
                    title=titleArray[1];
                    subTitle="Car Accident Car Accident Car Accident";
                    imageURL=R.drawable.health5;
                    break;
                case 3:
                    title=titleArray[2];
                    subTitle="Car Accident Car Accident Car Accident";
                    imageURL=R.drawable.health3;
                    break;
                case 4:
                    title=titleArray[3];
                    subTitle="Car Accident Car Accident Car Accident";
                    imageURL=R.drawable.health2;
                    break;
                case 5:
                    title=titleArray[4];
                    subTitle="Car Accident Car Accident Car Accident";
                    imageURL=R.drawable.health;
                    break;
                case 6:
                    title=titleArray[5];
                    subTitle="Car Accident Car Accident Car Accident";
                    imageURL=R.drawable.health4;
                    break;
                case 7:
                    title=titleArray[6];
                    subTitle="Car Accident Car Accident Car Accident";
                    imageURL=R.drawable.health;
                    break;

                case 8:
                    title=titleArray[7];
                    subTitle="Car Accident Car Accident Car Accident";
                    imageURL=R.drawable.health4;
                    break;

                case 9:
                    title=titleArray[8];
                    subTitle="Car Accident Car Accident Car Accident";
                    imageURL=R.drawable.health;
                    break;

                case 10:
                    title=titleArray[9];
                    subTitle="Car Accident Car Accident Car Accident";
                    imageURL=R.drawable.health4;
                    break;
                    default:
                        break;


            }
            mAccidentDatas.setTitle(title);
            mAccidentDatas.setImageURL(imageURL);
            mAccidentDatas.setSubTitle(subTitle);
            mHelpCentersData.add(mAccidentDatas);


        }


    }
    List<AccidentDatas> getAccidentsData()
    {
        return mHelpCentersData;
    }
    public AccidentDatas getAccidentData(int position)
    {
        for(AccidentDatas helpCenter:mHelpCentersData)
        {
            if(helpCenter.getHelpCenterId().equals(position))
            {
                return  helpCenter;
            }
        }
        return null;


    }




}
