package com.wildsmith.material.presentation;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.wildsmith.material.R;
import com.wildsmith.material.core.CoreMaterialActivity;

public class PresentationActivity extends CoreMaterialActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getActionBar();
        actionBar.show();
        actionBar.setTitle(null);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
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