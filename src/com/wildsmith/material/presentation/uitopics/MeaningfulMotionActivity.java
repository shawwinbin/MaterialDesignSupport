package com.wildsmith.material.presentation.uitopics;

import android.app.FragmentManager;
import android.os.Bundle;

import com.wildsmith.material.presentation.PresentationActivity;

public class MeaningfulMotionActivity extends PresentationActivity {

    private MeaningfulMotionFragment meaningfulMotionFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fm = getFragmentManager();
        meaningfulMotionFragment = (MeaningfulMotionFragment) fm.findFragmentByTag(MeaningfulMotionFragment.TAG);

        if (meaningfulMotionFragment == null) {
            meaningfulMotionFragment = MeaningfulMotionFragment.newInstance();
            addFragment(meaningfulMotionFragment, MeaningfulMotionFragment.TAG);
        }
    }
}