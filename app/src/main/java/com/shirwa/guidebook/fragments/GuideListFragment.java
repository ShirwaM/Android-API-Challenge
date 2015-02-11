package com.shirwa.guidebook.fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.shirwa.guidebook.R;
import com.shirwa.guidebook.adapters.GuideAdapter;
import com.shirwa.guidebook.adapters.ViewPagerAdapter;
import com.shirwa.guidebook.utils.Guide;
import com.shirwa.guidebook.utils.GuidebookClient;

import java.util.ArrayList;

/**
 * Created by shirwamohamed on 1/20/15.
 */
public class GuideListFragment extends Fragment {
    private RecyclerView mList;
    private GuideAdapter adapter;
    private ProgressBar loadingBar;
    private ViewPagerAdapter viewPagerAdapter;

    public GuideListFragment() {
    }

    public GuideListFragment(ViewPagerAdapter viewPagerAdapter) {
        this.viewPagerAdapter = viewPagerAdapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.guide_fragment, container, false);
        mList = (RecyclerView) view.findViewById(R.id.main_list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mList.setLayoutManager(mLayoutManager);
        loadingBar = (ProgressBar) view.findViewById(R.id.loading_bar);
        adapter = new GuideAdapter(getActivity(), viewPagerAdapter);
        new HttpAsyncTask().execute();
        return view;
    }

    private class HttpAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            ArrayList<Guide> list = new GuidebookClient().getData();
            adapter.add(list);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            loadingBar.setVisibility(View.INVISIBLE);
            adapter.notifyDataSetChanged();
            mList.setAdapter(adapter);
        }
    }
}
