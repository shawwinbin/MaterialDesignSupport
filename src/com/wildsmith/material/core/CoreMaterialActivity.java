package com.wildsmith.material.core;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wildsmith.material.R;
import com.wildsmith.material.core.CoreMaterialFragment.CoreMaterialFragmentListener;
import com.wildsmith.material.core.CoreMaterialListFragment.CoreMaterialListFragmentListener;
import com.wildsmith.material.drawer.DrawerListRowAdapter;
import com.wildsmith.material.drawer.DrawerListRowFactory;
import com.wildsmith.material.drawer.DrawerOnClickListener;
import com.wildsmith.material.drawer.DrawerToggleListener;
import com.wildsmith.material.list.expandable.AnimatedExpandableListView;

public abstract class CoreMaterialActivity extends Activity implements CoreMaterialFragmentListener, CoreMaterialListFragmentListener {

    public static final String ACTION_BAR_SHOWING = "ActionBarShowing";

    private DrawerToggleListener mDrawerToggleListener;

    protected DrawerListRowAdapter mDrawerAdapter;

    protected Boolean mActionBarShowing;

    private String mCurrentFragmentTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.core_material);

        if (isActionBarNecessary() == false) {
            ActionBar actionBar = getActionBar();
            actionBar.hide();
            return;
        }

        setupActionBarVisibility();
        setupActionBarAttributes();
        setupLeftNavigationMenu();
        setupLeftNavigationClickListener();
    }

    private void setupActionBarVisibility() {
        final ActionBar actionBar = getActionBar();
        if (actionBar == null) {
            return;
        }

        final Intent intent = getIntent();
        if (intent == null) {
            mActionBarShowing = true;
            actionBar.show();
            return;
        }

        final Bundle bundle = intent.getExtras();
        if (bundle == null) {
            mActionBarShowing = true;
            actionBar.show();
            return;
        }

        mActionBarShowing = bundle.getBoolean(ACTION_BAR_SHOWING);
        if (mActionBarShowing == null || mActionBarShowing) {
            mActionBarShowing = true;
            actionBar.show();
            return;
        }

        mActionBarShowing = false;
        actionBar.hide();
    }

    private void setupActionBarAttributes() {
        final ActionBar actionBar = getActionBar();
        if (actionBar == null) {
            return;
        }

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
    }

    private void setupLeftNavigationMenu() {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        LinearLayout drawer = (LinearLayout) findViewById(R.id.left_drawer);
        View drawerContent =
                LayoutInflater.from(this).inflate(R.layout.core_material_drawer, new RelativeLayout(CoreMaterialApplication.getContext()),
                        false);
        drawer.addView(drawerContent);

        AnimatedExpandableListView drawerList = (AnimatedExpandableListView) findViewById(R.id.left_drawer_list);
        mDrawerAdapter = new DrawerListRowAdapter(drawerList, this);
        drawerList.setAdapter(mDrawerAdapter);

        setupDrawerListRows(true);
    }

    public void setupDrawerListRows(boolean includeActionBarRow) {
        AnimatedExpandableListView drawerList = (AnimatedExpandableListView) findViewById(R.id.left_drawer_list);
        TextView drawerListTitle = (TextView) findViewById(R.id.left_drawer_title);

        mDrawerAdapter.setRows(DrawerListRowFactory.createDrawerListRows(this, drawerList, drawerListTitle, includeActionBarRow));
        mDrawerAdapter.notifyDataSetChanged();
    }

    public DrawerOnClickListener createNewDrawerOnClickListener(int position) {
        LinearLayout drawer = (LinearLayout) findViewById(R.id.left_drawer);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        DrawerOnClickListener itemClickListener =
                new DrawerOnClickListener(this, drawer, drawerLayout, R.array.drawer_classes, position, true);
        return itemClickListener;
    }

    public DrawerOnClickListener createNewChildDrawerOnClickListener(int resId, int position) {
        LinearLayout drawer = (LinearLayout) findViewById(R.id.left_drawer);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        DrawerOnClickListener itemClickListener = new DrawerOnClickListener(this, drawer, drawerLayout, resId, position, false);
        return itemClickListener;
    }

    private void setupLeftNavigationClickListener() {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggleListener =
                new DrawerToggleListener(this, drawerLayout, getNavigationResource(), R.string.drawer_open, R.string.drawer_close);
        drawerLayout.setDrawerListener(mDrawerToggleListener);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (mDrawerToggleListener != null) {
            mDrawerToggleListener.syncState();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (mDrawerToggleListener != null) {
            mDrawerToggleListener.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggleListener != null && mDrawerToggleListener.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void handleFragment(Fragment content, String tag, boolean add) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.content, content, tag);
        if (add && ft.isAddToBackStackAllowed() && fm.getBackStackEntryCount() != 0) {
            ft.addToBackStack(tag);
        }

        ft.commit();
    }

    public void addFragment(Fragment content, String fragmentTag) {
        handleFragment(content, fragmentTag, true);
    }

    public void replaceFragment(Fragment content, String fragmentTag) {
        handleFragment(content, fragmentTag, false);
    }

    public String getCurrentFragmentTag() {
        return mCurrentFragmentTag;
    }

    @Override
    public void setCurrentFragmentTag(String tag) {
        mCurrentFragmentTag = tag;
    }

    protected abstract int getNavigationResource();

    protected abstract boolean isActionBarNecessary();
}