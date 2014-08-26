package com.wildsmith.material.presentation.devtopics;

import android.app.FragmentManager;
import android.os.Bundle;

import com.wildsmith.material.presentation.PresentationActivity;

public class ViewClippingActivity extends PresentationActivity {

    private ViewClippingFragment viewClippingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fm = getFragmentManager();
        viewClippingFragment = (ViewClippingFragment) fm.findFragmentByTag(ViewClippingFragment.TAG);

        if (viewClippingFragment == null) {
            viewClippingFragment = ViewClippingFragment.newInstance();
            addFragment(viewClippingFragment, ViewClippingFragment.TAG);
        }
    }
}