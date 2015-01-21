package com.shirwa.guidebook.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shirwa.guidebook.MainActivity;
import com.shirwa.guidebook.R;
import com.shirwa.guidebook.utils.DrawerItem;

import java.util.List;

/**
 * Created by shirwamohamed on 1/20/15.
 */
public class DrawerItemAdapter extends RecyclerView.Adapter<DrawerItemAdapter.ViewHolder> implements View.OnClickListener {
    public navItemClickCallback mCallback;
    public List<DrawerItem> mDataSet;
    int selectedIndex = 0;

    public interface navItemClickCallback {
        public void clickCallback(String path);
    }

    @Override
    public void onClick(View view) {
        int itemIndex = (Integer) view.getTag();
        selectedIndex = itemIndex;
        Log.v("Clicked!", "Index: " + selectedIndex);
        notifyDataSetChanged();
        MainActivity.closeDrawer();
        mCallback.clickCallback(mDataSet.get(itemIndex).getTitle());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout root;
        public TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            root = (LinearLayout) itemView;
            title = (TextView) itemView.findViewById(R.id.drawer_item_text);
        }
    }

    public DrawerItemAdapter(List<DrawerItem> items, navItemClickCallback callback) {
        mDataSet = items;
        mCallback = callback;
    }

    @Override
    public DrawerItemAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_drawer, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DrawerItemAdapter.ViewHolder viewHolder, int i) {
        if (i == selectedIndex)
            viewHolder.root.setActivated(true);
        else
            viewHolder.root.setActivated(false);
        viewHolder.title.setText(mDataSet.get(i).getTitle());
        viewHolder.root.setTag(i);
        viewHolder.root.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
