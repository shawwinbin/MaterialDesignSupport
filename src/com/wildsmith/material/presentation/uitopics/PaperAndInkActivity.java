package com.wildsmith.material.presentation.uitopics;

import android.app.FragmentManager;
import android.os.Bundle;

import com.wildsmith.material.presentation.PresentationActivity;

public class PaperAndInkActivity extends PresentationActivity {

    private PaperAndInkFragment paperAndInkFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fm = getFragmentManager();
        paperAndInkFragment = (PaperAndInkFragment) fm.findFragmentByTag(PaperAndInkFragment.TAG);

        if (paperAndInkFragment == null) {
            paperAndInkFragment = PaperAndInkFragment.newInstance();
            addFragment(paperAndInkFragment, PaperAndInkFragment.TAG);
        }
    }
}