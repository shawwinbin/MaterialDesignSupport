package com.wildsmith.material.presentation.devtopics;

import android.app.FragmentManager;
import android.os.Bundle;

import com.wildsmith.material.presentation.PresentationActivity;

public class RippleActivity extends PresentationActivity {

    private RippleFragment rippleFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fm = getFragmentManager();
        rippleFragment = (RippleFragment) fm.findFragmentByTag(RippleFragment.TAG);

        if (rippleFragment == null) {
            rippleFragment = RippleFragment.newInstance();
            addFragment(rippleFragment, RippleFragment.TAG);
        }
    }
}