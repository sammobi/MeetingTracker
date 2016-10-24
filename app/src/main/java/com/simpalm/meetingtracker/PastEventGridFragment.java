package com.simpalm.meetingtracker;


import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PastEventGridFragment extends Fragment {
    private RecyclerView mRecyclerView;

    ArrayList<EventDetail> mArraylist;

    private PastRecyclerAdapter mPastEventRecyclerAdapter;


    public PastEventGridFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        PastFragment pastFragment = new PastFragment();


        ArrayList<EventDetail> mArraylist = pastFragment.getNumberList();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.recyclerview, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
        // First param is number of columns and second param is orientation i.e Vertical or Horizontal
        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
// Attach the layout manager to the recycler view
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mPastEventRecyclerAdapter = new PastRecyclerAdapter(getActivity(), mArraylist);
        mRecyclerView.setAdapter(mPastEventRecyclerAdapter);
        mPastEventRecyclerAdapter.setHideDetail(true);
        return view;
    }

    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;

            // Add top margin only for the first item to avoid double space between items
            if (parent.getChildLayoutPosition(view) == 0) {
                outRect.top = space;
            } else {
                outRect.top = 0;
            }
        }
    }

}
