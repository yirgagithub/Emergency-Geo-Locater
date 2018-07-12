package com.the.example.amorageolocater.health;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.the.example.amorageolocater.AccidentDatas;
import com.the.example.amorageolocater.LocationProviderClass;
import com.the.example.amorageolocater.R;

import java.util.List;

/**
 * Created by what on 7/15/2017.
 */

public class HealthRecyclerViewAdapter extends RecyclerView.Adapter<HealthRecyclerViewAdapter.ListViewHolder> {

    public static final String ACCIDENT_TYPE ="accident type" ;
    public static final String CATEGORY ="category" ;
    private List<AccidentDatas> listAccidentDatas;
    private Context mContext;

    HealthRecyclerViewAdapter(List<AccidentDatas> listAccidentDatas, Context context)
    {
        this.listAccidentDatas = listAccidentDatas;
        this.mContext=context;
    }
     class ListViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mTitleTextView;
        private TextView msubTitle;

        ListViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.list_imageView_id);
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_titleTextView_id);
            msubTitle = (TextView) itemView.findViewById(R.id.list_subTitle_id);
            final String accidentType;
            final String category="Health-Care";
            int item=getAdapterPosition();
            switch (item)
            {
                case 0:
                    accidentType="Health-Care 1";
                    break;
                case 1:
                    accidentType="Health-Care 2";
                    break;
                case 2:
                    accidentType="Health-Care 3";
                    break;
                case 3:
                    accidentType="Health-Care 4";
                    break;
                case 4:
                    accidentType="Health-Care 5";
                    break;
                case 5:
                    accidentType="Health-Care 6";
                    break;
                case 6:
                    accidentType="Health-Care 7";
                    break;
                case 7:
                    accidentType="Health-Care 8";
                    break;
                case 8:
                    accidentType="Health-Care 9";
                    break;
                case 9:
                    accidentType="Health-Care 10";
                    break;
                default:
                    accidentType="Child Birth Emergency";
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(mContext, LocationProviderClass.class);
                    intent.putExtra(ACCIDENT_TYPE,accidentType);
                    intent.putExtra(CATEGORY,category);
                    mContext.startActivity(intent);
                }
            });

        }

    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view= inflater.inflate(R.layout.list_row_layout,parent,false);
        return new ListViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {

        AccidentDatas accidentDatas = listAccidentDatas.get(position);
        Drawable drawable;
        BitmapDrawable bitmapDrawable;
        Bitmap bitmap=null;

        holder.mTitleTextView.setText(accidentDatas.getTitle());
        holder.msubTitle.setText(accidentDatas.getSubTitle());
        Glide.with(mContext)
                .load(accidentDatas.getImageURL())
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return listAccidentDatas.size();
    }
}


