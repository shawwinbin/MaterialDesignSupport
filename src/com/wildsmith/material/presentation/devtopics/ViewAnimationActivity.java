package com.wildsmith.material.presentation.devtopics;

import android.app.FragmentManager;
import android.os.Bundle;

import com.wildsmith.material.presentation.PresentationActivity;

public class ViewAnimationActivity extends PresentationActivity {

    private ViewAnimationFragment viewAnimationFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fm = getFragmentManager();
        viewAnimationFragment = (ViewAnimationFragment) fm.findFragmentByTag(ViewAnimationFragment.TAG);

        if (viewAnimationFragment == null) {
            viewAnimationFragment = ViewAnimationFragment.newInstance();
            addFragment(viewAnimationFragment, ViewAnimationFragment.TAG);
        }
    }
}