package com.wildsmith.material.interstitial;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.wildsmith.material.R;
import com.wildsmith.material.core.CoreMaterialActivity;

public class InterstitialActivity extends CoreMaterialActivity {

    private InterstitialFragment interstitialFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getActionBar();
        actionBar.show();
        actionBar.setTitle(null);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        interstitialFragment = new InterstitialFragment();
        addFragment(interstitialFragment, InterstitialFragment.TAG);
    }

    @Override
    protected int getNavigationResource() {
        return R.drawable.ic_drawer;
    }

    @Override
    protected boolean isActionBarNecessary() {
        return true;
    }
}