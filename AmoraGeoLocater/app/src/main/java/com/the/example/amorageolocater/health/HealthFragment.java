package com.the.example.amorageolocater.health;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.the.example.amorageolocater.AccidentDatas;
import com.the.example.amorageolocater.R;
import com.the.example.amorageolocater.SpaceItemDocoration;

import java.util.List;

public class HealthFragment extends Fragment {


    public HealthFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View  view= inflater.inflate(R.layout.fragment_list, container, false);
        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.list_recyclerview_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        int spanCount = 1; // 1 columns
        int spacing = 20; // 20px
        recyclerView.addItemDecoration(new SpaceItemDocoration(spanCount, spacing, true));
         ListHealthData listHelperCenterData=ListHealthData.get(getContext());
        List<AccidentDatas> helpCentersData= listHelperCenterData.getAccidentsData();
        HealthRecyclerViewAdapter healthRecyclerViewAdapter =new HealthRecyclerViewAdapter(helpCentersData,getContext());
        recyclerView.setAdapter(healthRecyclerViewAdapter);

        return view;
    }



}
