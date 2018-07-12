package com.the.example.amorageolocater;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.the.example.amorageolocater.database.HelpCenterDataDb;

import java.util.List;

/**
 * Created by what on 11/4/2017.
 */

class ViewEditDatabaseAdapter extends RecyclerView.Adapter<ViewEditDatabaseAdapter.ListViewHolder>
{
    private List<HelpCenterDataDb> list;
     ViewEditDatabaseAdapter(List<HelpCenterDataDb> list)
    {
        this.list=list;
    }



    class ListViewHolder extends RecyclerView.ViewHolder
    {
        private TextView first;
        private TextView second;
        private TextView third;
        private TextView fourth;

         ListViewHolder(View itemView) {
            super(itemView);

            first=(TextView)itemView.findViewById(R.id.first);
            second=(TextView)itemView.findViewById(R.id.second);
            third=(TextView)itemView.findViewById(R.id.third);
            fourth=(TextView)itemView.findViewById(R.id.fourth);


            first.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {

                }
            });

        }


    }
    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.edit_database,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position)
    {
            HelpCenterDataDb helpCenterDataDb=list.get(position);
            holder.first.setText(helpCenterDataDb.getPhoneNumber());
            holder.second.setText(helpCenterDataDb.getUrlPath());
            holder.third.setText(String.valueOf(helpCenterDataDb.getLatitude()));
            holder.fourth.setText(String.valueOf(helpCenterDataDb.getLongitude()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



}
