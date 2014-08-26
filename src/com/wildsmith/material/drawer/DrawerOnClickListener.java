package com.wildsmith.material.drawer;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.wildsmith.material.core.CoreMaterialActivity;
import com.wildsmith.material.core.CoreMaterialFragment;
import com.wildsmith.material.core.CoreMaterialListFragment;
import com.wildsmith.material.interstitial.InterstitialActivity;
import com.wildsmith.material.utils.ResourceHelper;

public class DrawerOnClickListener implements OnClickListener {

    private static final String TAG = DrawerOnClickListener.class.getSimpleName();

    private CoreMaterialActivity mActivity;

    private LinearLayout mDrawer;

    private DrawerLayout mDrawerLayout;

    private String[] mClasses;

    private int mPosition;

    private boolean mIsParent;

    public DrawerOnClickListener(CoreMaterialActivity activity, LinearLayout drawer, DrawerLayout drawerLayout, int resId, int position,
            boolean isParent) {
        mActivity = activity;
        mDrawer = drawer;
        mDrawerLayout = drawerLayout;
        mClasses = ResourceHelper.getStringArray(resId);
        mPosition = position;
        mIsParent = isParent;
    }

    @Override
    public void onClick(View v) {
        int position = mPosition;
        if (mIsParent) {
            position = adjustPostionForDividerRows(mClasses.length, mPosition);
        }
        selectItem(position);
    }

    public void selectItem(int position) {
        Fragment fragment = null;
        Activity activity = null;

        try {
            Class<?> cls = Class.forName(mClasses[position]);
            if (cls != null) {
                Object potential = cls.newInstance();
                if (potential instanceof Fragment) {
                    fragment = (Fragment) potential;
                } else if (potential instanceof Activity) {
                    activity = (Activity) potential;
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Could not create the requested material class." + e.getMessage());
            return;
        }

        if (fragment != null) {
            handleFragmentItemClick(fragment);
        } else if (activity != null) {
            handleActivityItemClick(activity);
        }

        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(mDrawer);
        }
    }

    private int adjustPostionForDividerRows(int length, int index) {
        if (index <= 1) {
            return index;
        }

        return (index / 2) + 1;
    }

    private void handleFragmentItemClick(Fragment fragment) {
        FragmentManager fm = mActivity.getFragmentManager();
        Fragment stackedFragment = fm.findFragmentByTag(mActivity.getCurrentFragmentTag());
        if (fragment.getClass().equals(stackedFragment.getClass())) {
            return;
        }

        String fragmentTag = null;
        if (fragment instanceof CoreMaterialFragment) {
            fragmentTag = ((CoreMaterialFragment) fragment).getFragmentTag();
        } else if (fragment instanceof CoreMaterialListFragment) {
            fragmentTag = ((CoreMaterialListFragment) fragment).getFragmentTag();
        }

        if (fragment instanceof CoreMaterialFragment || fragment instanceof CoreMaterialListFragment) {
            mActivity.replaceFragment(fragment, fragmentTag);
        }
    }

    private void handleActivityItemClick(Activity activity) {
        if (activity.getClass().equals(mActivity.getClass())) {
            return;
        }

        Intent intent = new Intent(mActivity, activity.getClass());
        mActivity.startActivity(intent);

        if (mActivity instanceof InterstitialActivity == false) {
            // mActivity.finishAfterTransition();
            mActivity.finish();
        }
    }
}