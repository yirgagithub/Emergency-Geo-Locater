package com.the.example.amorageolocater.traffic;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.the.example.amorageolocater.AccidentDatas;
import com.the.example.amorageolocater.LocationProviderClass;
import com.the.example.amorageolocater.NearByHelpCenterFinder;
import com.the.example.amorageolocater.R;


import java.util.List;

/**
 * Created by what on 8/1/2017.
 */

public class TrafficRecyclerViewAdapter extends RecyclerView.Adapter<TrafficRecyclerViewAdapter.GridViewHolder> {

    public static final String ACCIDENT_TYPE ="accident type" ;
    public static final String CATEGORY ="category" ;
    private List<AccidentDatas> listAccidentDatas;
    private Context mContext;
    TrafficRecyclerViewAdapter(List<AccidentDatas> listAccidentDatas,Context context)
    {
        this.listAccidentDatas = listAccidentDatas;
        this.mContext=context;
    }
    class GridViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView mImageView;
        private TextView mTextView;
        AccidentDatas mAccidentDatas;
        int viewPosition;
        GridViewHolder(View itemView) {
            super(itemView);
            mImageView=(ImageView)itemView.findViewById(R.id.gridRow_Button_id);
            mTextView=(TextView)itemView.findViewById(R.id.grid_textView_id);
            final String accidentType;
            final String category="Traffic";
            int item=getAdapterPosition();
            switch (item)
            {
                case 0:
                    accidentType="Traffic-Accident 1";
                    break;
                case 1:
                    accidentType="Traffic-Accident 2";
                    break;
                case 2:
                    accidentType="Traffic-Accident 3";
                    break;
                case 3:
                    accidentType="Traffic-Accident 4";
                    break;
                case 4:
                    accidentType="Traffic-Accident 5";
                    break;
                case 5:
                    accidentType="Traffic-Accident 6";
                    break;
                case 6:
                    accidentType="Traffic-Accident 7";
                    break;
                case 7:
                    accidentType="Traffic-Accident 8";
                    break;
                case 8:
                    accidentType="Traffic-Accident 9";
                    break;
                case 9:
                    accidentType="Traffic-Accident 10";
                    break;
                default:
                    accidentType="Traffic-Accident";
            }



            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {

                    Intent intent=new Intent(mContext,LocationProviderClass.class);
                    intent.putExtra(ACCIDENT_TYPE,accidentType);
                    intent.putExtra(CATEGORY,category);
                     mContext.startActivity(intent);

                }
            });
        }
        void bindData(AccidentDatas accidentDatas, int position)
        {
            mAccidentDatas = accidentDatas;
            viewPosition=position;
            mTextView.setText(accidentDatas.getTitle());
            Glide.with(mContext)
                    .load(accidentDatas.getUrl())
                    .into(mImageView);
        }
    }
    @Override
    public TrafficRecyclerViewAdapter.GridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view= inflater.inflate(R.layout.grid_row_layout,parent,false);

        return new TrafficRecyclerViewAdapter.GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TrafficRecyclerViewAdapter.GridViewHolder holder, int position) {

        final AccidentDatas accidentDatas = listAccidentDatas.get(position);
        holder.bindData(accidentDatas,position);


    }

    @Override
    public int getItemCount() {
        return listAccidentDatas.size();
    }


        }


