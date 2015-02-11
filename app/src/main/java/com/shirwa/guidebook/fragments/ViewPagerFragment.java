package com.shirwa.guidebook.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shirwa.guidebook.MainActivity;
import com.shirwa.guidebook.R;
import com.shirwa.guidebook.adapters.ViewPagerAdapter;

/**
 * Created by shirwamohamed on 2/10/15.
 */
public class ViewPagerFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_pager_fragment, null);
        ViewPager pager = (ViewPager) view.findViewById(R.id.view_pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        pager.setAdapter(adapter);
        return view;
    }
}
