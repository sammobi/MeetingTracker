package com.simpalm.meetingtracker;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PastFragment extends Fragment {

    private RecyclerView mRecyclerView;

    private ArrayList<EventDetail> mEventDetailArrayList;

    private PastRecyclerAdapter mPastRecyclerAdapter;


    public PastFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ArrayList<EventDetail> arrayList = getNumberList();


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.recyclerview, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
        mPastRecyclerAdapter = new PastRecyclerAdapter(getActivity(), arrayList);
        mRecyclerView.setAdapter(mPastRecyclerAdapter);

        return view;


    }

    public ArrayList<EventDetail> getNumberList() {
        mEventDetailArrayList = new ArrayList<EventDetail>();

        EventDetail eventDetail1 = new EventDetail();
        eventDetail1.setEventTitle("Simpalm Anniversary");
        eventDetail1.setEventDesc("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dum. ");
        eventDetail1.setEventDateTime("10-22-2016 12:30 PM");
        eventDetail1.setEventLocation("15602 Marathon Cir Apt 204, Gaithersburg MD 20878");
        mEventDetailArrayList.add(eventDetail1);

        EventDetail eventDetail2 = new EventDetail();
        eventDetail2.setEventTitle("Simpalm Anniversary");
        eventDetail2.setEventDesc("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dum. ");
        eventDetail2.setEventDateTime("10-22-2016 12:30 PM");
        eventDetail2.setEventLocation("15602 Marathon Cir Apt 204, Gaithersburg MD 20878");
        mEventDetailArrayList.add(eventDetail2);

        EventDetail eventDetail3 = new EventDetail();
        eventDetail3.setEventTitle("Simpalm Anniversary");
        eventDetail3.setEventDesc("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dum. ");
        eventDetail3.setEventDateTime("10-22-2016 12:30 PM");
        eventDetail3.setEventLocation("15602 Marathon Cir Apt 204, Gaithersburg MD 20878");
        mEventDetailArrayList.add(eventDetail3);

        EventDetail eventDetail4 = new EventDetail();
        eventDetail4.setEventTitle("Simpalm Anniversary");
        eventDetail4.setEventDesc("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dum. ");
        eventDetail4.setEventDateTime("10-22-2016 12:30 PM");
        eventDetail4.setEventLocation("15602 Marathon Cir Apt 204, Gaithersburg MD 20878");
        mEventDetailArrayList.add(eventDetail4);

        EventDetail eventDetail5 = new EventDetail();
        eventDetail5.setEventTitle("Simpalm Anniversary");
        eventDetail5.setEventDesc("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dum. ");
        eventDetail5.setEventDateTime("10-22-2016 12:30 PM");
        eventDetail5.setEventLocation("15602 Marathon Cir Apt 204, Gaithersburg MD 20878");
        mEventDetailArrayList.add(eventDetail5);
        return mEventDetailArrayList;

    }


}
