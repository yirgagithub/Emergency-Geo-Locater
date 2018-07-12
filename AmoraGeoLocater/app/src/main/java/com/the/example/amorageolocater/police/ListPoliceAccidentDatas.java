package com.the.example.amorageolocater.police;

import android.content.Context;

import com.the.example.amorageolocater.AccidentDatas;
import com.the.example.amorageolocater.R;
import com.the.example.amorageolocater.traffic.ListTrafficAccidentDatas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by what on 8/1/2017.
 */

public class ListPoliceAccidentDatas {

    private static ListPoliceAccidentDatas mListPoliceAccidentDatas;
    private List<AccidentDatas>  mPoliceDatas;
    private  Context mContext;


    static ListPoliceAccidentDatas get(Context context)
    {
        if(mListPoliceAccidentDatas==null)
            mListPoliceAccidentDatas =new ListPoliceAccidentDatas(context);
        return mListPoliceAccidentDatas;
    }

    private ListPoliceAccidentDatas(Context context)
    {

        int index;
        int imageURL=0;
        String[] titleArrays=context.getResources().getStringArray(R.array.police_list);
        String title="";
        mPoliceDatas=new ArrayList<>();
        for(index=0;index<10;index++)
        {
            AccidentDatas mAccidentDatas = new AccidentDatas();
            switch (index+1)
            {
                case 1:
                    title=titleArrays[0];
                    imageURL= R.drawable.police;
                    break;
                case 2:
                    title=titleArrays[1];
                    imageURL=R.drawable.police2;
                    break;
                case 3:
                    title=titleArrays[2];
                    imageURL=R.drawable.police3;
                    break;
                case 4:
                    title=titleArrays[3];
                    imageURL=R.drawable.police4;
                    break;
                case 5:
                    title=titleArrays[4];
                    imageURL=R.drawable.police5;
                    break;
                case 6:
                    title=titleArrays[5];
                    imageURL=R.drawable.police6;
                    break;
                case 7:
                    title=titleArrays[6];
                    imageURL=R.drawable.police7;
                    break;

                case 8:
                    title=titleArrays[7];
                    imageURL=R.drawable.police8;
                    break;

                case 9:
                    title=titleArrays[8];
                    imageURL=R.drawable.police9;
                    break;

                case 10:
                    title="Police rule5";
                    imageURL=R.drawable.police9;
                    break;
                default:
                    break;


            }
            mAccidentDatas.setTitle(title);
            mAccidentDatas.setImageURL(imageURL);
            mPoliceDatas.add(mAccidentDatas);


        }

    }
    List<AccidentDatas> getAccidentsData()
    {
        return mPoliceDatas;
    }
    public AccidentDatas getAccidentData(int position) {
        for (AccidentDatas helpCenter : mPoliceDatas) {
            if (helpCenter.getHelpCenterId().equals(position)) {
                return helpCenter;
            }
        }
        return null;
    }
}
