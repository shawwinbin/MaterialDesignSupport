package com.wildsmith.material.drawer;

import android.app.Activity;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

public class DrawerToggleListener extends ActionBarDrawerToggle {

    private Activity mActivity;

    public DrawerToggleListener(Activity activity, DrawerLayout drawerLayout, int drawerImageRes, int openDrawerContentDescRes,
            int closeDrawerContentDescRes) {
        super(activity, drawerLayout, drawerImageRes, openDrawerContentDescRes, closeDrawerContentDescRes);

        mActivity = activity;
    }

    @Override
    public void onDrawerClosed(View view) {
        super.onDrawerClosed(view);
        mActivity.invalidateOptionsMenu();
    }

    @Override
    public void onDrawerOpened(View drawerView) {
        super.onDrawerOpened(drawerView);
        mActivity.invalidateOptionsMenu();
    }
}