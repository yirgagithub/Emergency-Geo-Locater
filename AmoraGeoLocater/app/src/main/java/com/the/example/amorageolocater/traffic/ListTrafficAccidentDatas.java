package com.the.example.amorageolocater.traffic;

import android.content.Context;

import com.the.example.amorageolocater.AccidentDatas;
import com.the.example.amorageolocater.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by what on 8/1/2017.
 */

public class ListTrafficAccidentDatas {

    private static ListTrafficAccidentDatas mListTrafficAccidentDatas;
    private  List<AccidentDatas> mTrafficDatas;
    private Context mContext;


    static ListTrafficAccidentDatas get(Context context)
    {

        if(mListTrafficAccidentDatas==null)
            mListTrafficAccidentDatas =new ListTrafficAccidentDatas(context);
        return mListTrafficAccidentDatas;
    }


    private ListTrafficAccidentDatas(Context context)
    {

        int index;
        int imageURL=0;
        String[] titleArray=context.getResources().getStringArray(R.array.traffic_title);
        String title="";
        mTrafficDatas=new ArrayList<>();
        for(index=0;index<10;index++)
        {
            AccidentDatas mAccidentDatas = new AccidentDatas();
            switch (index+1)
            {
                case 1:
                    title=titleArray[0];
                    imageURL= R.drawable.image2;
                    break;
                case 2:
                    title=titleArray[1];
                    imageURL=R.drawable.traffic;
                    break;
                case 3:
                    title=titleArray[2];
                    imageURL=R.drawable.traffic2;
                    break;
                case 4:
                    title=titleArray[3];
                    imageURL=R.drawable.image5;
                    break;
                case 5:
                    title=titleArray[4];
                    imageURL=R.drawable.image5;
                    break;
                case 6:
                    title=titleArray[5];
                    imageURL=R.drawable.image7;
                    break;
                case 7:
                    title=titleArray[6];
                    imageURL=R.drawable.traffic;
                    break;

                case 8:
                    title=titleArray[7];
                    imageURL=R.drawable.image4;
                    break;

                case 9:
                    title=titleArray[8];
                    imageURL=R.drawable.traffic2;
                    break;

                case 10:
                    title="Police rule5";
                    imageURL=R.drawable.image7;
                    break;
                default:
                    break;


            }
            mAccidentDatas.setTitle(title);
            mAccidentDatas.setImageURL(imageURL);
            mTrafficDatas.add(mAccidentDatas);


      }

    }
    List<AccidentDatas> getHelpCentersData()
    {
        return mTrafficDatas;
    }
    public AccidentDatas getHelpCenterData(int position) {
        for (AccidentDatas helpCenter : mTrafficDatas) {
            if (helpCenter.getHelpCenterId().equals(position)) {
                return helpCenter;
            }
        }
        return null;
    }




}
