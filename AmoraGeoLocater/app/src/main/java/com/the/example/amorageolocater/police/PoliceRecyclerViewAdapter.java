package com.the.example.amorageolocater.police;

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
 * Created by what on 7/14/2017.
 */

public class PoliceRecyclerViewAdapter extends RecyclerView.Adapter<PoliceRecyclerViewAdapter.GridViewHolder>{

    public static final String ACCIDENT_TYPE ="accident type" ;
    public static final String CATEGORY ="category" ;
    private List<AccidentDatas> listAccidentDatas;
    private Context mContext;

    PoliceRecyclerViewAdapter(List<AccidentDatas> listAccidentDatas, Context context)
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
            int item=getAdapterPosition();
             final String accidentType;
             final String category="police";
            switch (item)
            {
                case 0:
                    accidentType="Robbery";
                    break;
                case 1:
                    accidentType="Terror Attack";
                    break;
                case 2:
                    accidentType="Fire Accident";
                    break;
                case 3:
                    accidentType="Suicide";
                    break;
                case 4:
                    accidentType="Corruption";
                    break;
                case 5:
                    accidentType="Police rule 1";
                    break;
                case 6:
                    accidentType="Police rule 2";
                    break;
                case 7:
                    accidentType="Police rule 3";
                    break;
                case 8:
                    accidentType="Police rule 4";
                    break;
                case 9:
                    accidentType="Police rule 5";
                    break;
               default:
                   accidentType="Police rule";
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
    public GridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view= inflater.inflate(R.layout.grid_row_layout,parent,false);

        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final GridViewHolder holder, int position) {

        final AccidentDatas accidentDatas = listAccidentDatas.get(position);
        holder.bindData(accidentDatas,position);


    }

    @Override
    public int getItemCount() {
        return listAccidentDatas.size();
    }
}
