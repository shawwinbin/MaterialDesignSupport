package com.wildsmith.material.presentation.uitopics;

import android.app.FragmentManager;
import android.os.Bundle;

import com.wildsmith.material.presentation.PresentationActivity;

public class VisualDesignActivity extends PresentationActivity {

    private VisualDesignFragment visualDesignFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fm = getFragmentManager();
        visualDesignFragment = (VisualDesignFragment) fm.findFragmentByTag(VisualDesignFragment.TAG);

        if (visualDesignFragment == null) {
            visualDesignFragment = VisualDesignFragment.newInstance();
            addFragment(visualDesignFragment, VisualDesignFragment.TAG);
        }
    }
}