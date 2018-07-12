package com.the.example.amorageolocater.traffic;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.the.example.amorageolocater.AccidentDatas;
import com.the.example.amorageolocater.R;
import com.the.example.amorageolocater.SpaceItemDocoration;

import java.util.List;


public class TrafficFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_grid, container, false);
        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.grid_recyclerview_id);
        int numberOfColumn = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numberOfColumn));
        int spanCount = 2; // 2 columns
        int spacing = 10; // 10px
        recyclerView.addItemDecoration(new SpaceItemDocoration(spanCount, spacing, true));
        ListTrafficAccidentDatas listTrafficAccidentDatas=ListTrafficAccidentDatas.get(getContext());
        List<AccidentDatas> accidentDatases=listTrafficAccidentDatas.getHelpCentersData();
        TrafficRecyclerViewAdapter trafficRecyclerViewAdapter=new TrafficRecyclerViewAdapter(accidentDatases,getContext());
        recyclerView.setAdapter(trafficRecyclerViewAdapter);
        return view;
    }

}