package com.shirwa.guidebook.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;;

import com.shirwa.guidebook.fragments.GuideListFragment;
import com.shirwa.guidebook.fragments.GuideWebViewFragment;

/**
 * Created by shirwamohamed on 2/10/15.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    GuideWebViewFragment webViewFragment;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new GuideListFragment(this);
        } else {
            webViewFragment = new GuideWebViewFragment();
            return webViewFragment;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    public void setUrl(String url) {
        webViewFragment.setUrl(url);
    }
}
