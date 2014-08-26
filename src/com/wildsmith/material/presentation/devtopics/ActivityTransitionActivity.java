package com.wildsmith.material.presentation.devtopics;

import android.app.FragmentManager;
import android.os.Bundle;

import com.wildsmith.material.presentation.PresentationActivity;

public class ActivityTransitionActivity extends PresentationActivity {

    private ActivityTransitionFragment activityTransitionFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fm = getFragmentManager();
        activityTransitionFragment = (ActivityTransitionFragment) fm.findFragmentByTag(ActivityTransitionFragment.TAG);

        if (activityTransitionFragment == null) {
            activityTransitionFragment = ActivityTransitionFragment.newInstance();
            addFragment(activityTransitionFragment, ActivityTransitionFragment.TAG);
        }
    }
}