package com.shirwa.guidebook.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.shirwa.guidebook.R;
import com.shirwa.guidebook.utils.Guide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shirwamohamed on 1/20/15.
 */
public class GuideAdapter extends RecyclerView.Adapter<GuideAdapter.ViewHolder> implements View.OnClickListener {
    private List<Guide> mItems;
    private Activity context;
    private ViewPagerAdapter viewPagerAdapter;

    public GuideAdapter(Activity context, ViewPagerAdapter viewPagerAdapter) {
        mItems = new ArrayList<Guide>();
        this.context = context;
        this.viewPagerAdapter = viewPagerAdapter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_guide, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Guide guide = mItems.get(i);
        viewHolder.name.setText(guide.getName());
        ImageLoader.getInstance().displayImage(guide.getIconUrl(), viewHolder.image);
        if (guide.getStartDate() != null)
            viewHolder.date.setText(guide.getStartDate().toString());
        viewHolder.root.setTag(i);
        viewHolder.root.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public void onClick(View view) {
        int index = (Integer) view.getTag();
        viewPagerAdapter.setUrl("http://www.guidebook.com/" + mItems.get(index).getLinkUrl());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView date;
        public ImageView image;
        public View root;

        public ViewHolder(View itemView) {
            super(itemView);
            root = itemView;
            name = (TextView) itemView.findViewById(R.id.name);
            date = (TextView) itemView.findViewById(R.id.date);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }

    public void add(List<Guide> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    public void add(Guide guide) {
        mItems.add(guide);
        notifyDataSetChanged();
    }
}
