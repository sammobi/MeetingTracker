package com.simpalm.meetingtracker;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpcomingEventGridFragment extends Fragment {
    private RecyclerView mRecyclerView;

    ArrayList<EventDetail> mArraylist;

    private RecyclerAdapter mRecyclerAdapter;


    public UpcomingEventGridFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        UpcomingFragment upcomingFragment = new UpcomingFragment();


        ArrayList<EventDetail> mArraylist = upcomingFragment.getNumberList();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.recyclerview, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);

        // First param is number of columns and second param is orientation i.e Vertical or Horizontal
        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
// Attach the layout manager to the recycler view
        mRecyclerView.setLayoutManager(gridLayoutManager);

        mRecyclerAdapter = new RecyclerAdapter(getActivity(), mArraylist);
        mRecyclerView.setAdapter(mRecyclerAdapter);


        return view;
    }

}
