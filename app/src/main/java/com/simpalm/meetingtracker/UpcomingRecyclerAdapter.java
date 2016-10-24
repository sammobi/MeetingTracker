package com.simpalm.meetingtracker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Simpalm on 7/21/16.
 */
public class UpcomingRecyclerAdapter extends RecyclerView.Adapter<UpcomingRecyclerAdapter.EventViewHolder> {

    Context mContext;

    private ArrayList<EventDetail> mEventDetail;

    public UpcomingRecyclerAdapter(Context mContext, ArrayList<EventDetail> mContactList) {
        this.mContext = mContext;
        this.mEventDetail = mContactList;
    }

    private boolean mHideDetail = false;

    public void setHideDetail(boolean hide) {
        mHideDetail = hide;

        notifyDataSetChanged();

    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragments_upcomingevents, parent, false);

        return new EventViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {

        final EventDetail eventDetail = mEventDetail.get(position);
        holder.mEventTitle.setText(eventDetail.getEventTitle());

        // in line if condition, if mhidetetail is true then view will be hidden if not then view will be visible.
        holder.mEventDesc.setVisibility(mHideDetail ? View.GONE : View.VISIBLE);
        holder.mEventDesc.setText(eventDetail.getEventDesc());
        holder.mEventDateTime.setText(eventDetail.getEventDateTime());
        holder.mEventLocation.setText(eventDetail.getEventLocation());

        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    @Override
    public int getItemCount() {
        if (mEventDetail == null)
            return 0;
        return mEventDetail.size();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder {

        private TextView mEventTitle, mEventDesc, mEventDateTime, mEventLocation;
        private ImageButton imageButton;

        public EventViewHolder(View itemView) {

            super(itemView);

            mEventTitle = (TextView) itemView.findViewById(R.id.upcomingEventTitle_TextView);
            mEventDesc = (TextView) itemView.findViewById(R.id.upcomingEventDesc_textView);
            mEventDateTime = (TextView) itemView.findViewById(R.id.upcomingEventDateTime_textView);
            mEventLocation = (TextView) itemView.findViewById(R.id.upcomingEventLocation_textView);
            imageButton = (ImageButton) itemView.findViewById(R.id.set_reminder_btn);

        }

    }
}

