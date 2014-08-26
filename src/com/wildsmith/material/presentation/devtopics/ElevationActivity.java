package com.wildsmith.material.presentation.devtopics;

import android.app.FragmentManager;
import android.os.Bundle;

import com.wildsmith.material.presentation.PresentationActivity;

public class ElevationActivity extends PresentationActivity {

    private ElevationFragment elevationFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fm = getFragmentManager();
        elevationFragment = (ElevationFragment) fm.findFragmentByTag(ElevationFragment.TAG);

        if (elevationFragment == null) {
            elevationFragment = ElevationFragment.newInstance();
            addFragment(elevationFragment, ElevationFragment.TAG);
        }
    }
}