package com.shirwa.guidebook;

import android.os.AsyncTask;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.shirwa.guidebook.adapters.DrawerItemAdapter;
import com.shirwa.guidebook.adapters.GuideAdapter;
import com.shirwa.guidebook.fragments.GuideListFragment;
import com.shirwa.guidebook.utils.DrawerItem;
import com.shirwa.guidebook.utils.Guide;
import com.shirwa.guidebook.utils.GuidebookClient;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private static DrawerLayout mDrawerLayout;
    private RecyclerView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        setUpDrawer();
        setUpImageLoader();
        getFragmentManager().beginTransaction().replace(R.id.content_frame, new GuideListFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setUpDrawer() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (RecyclerView) findViewById(R.id.left_drawer);
        mDrawerList.setHasFixedSize(true);
        DrawerItemAdapter adapter = new DrawerItemAdapter(getDummyItems(), new DrawerItemAdapter.navItemClickCallback() {
            @Override
            public void clickCallback(String path) {
                Toast.makeText(MainActivity.this, path + " was clicked", Toast.LENGTH_LONG).show();
            }
        });
        mDrawerList.setLayoutManager(mLayoutManager);
        mDrawerList.setAdapter(adapter);
        setUpDrawerToggle();
    }

    public void setUpImageLoader() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .resetViewBeforeLoading(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(options)
                .build();
        ImageLoader.getInstance().init(config);
    }

    private void setUpDrawerToggle() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                supportInvalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                supportInvalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle.syncState();
    }

    private List<DrawerItem> getDummyItems() {
        List<DrawerItem> items = new ArrayList<>();
        items.add(new DrawerItem("Home"));
        items.add(new DrawerItem("Account Info"));
        items.add(new DrawerItem("Log Out"));
        return items;
    }

    public static void closeDrawer() {
        mDrawerLayout.closeDrawers();
    }


}
