package com.wildsmith.material.presentation.uitopics;

import android.app.FragmentManager;
import android.os.Bundle;

import com.wildsmith.material.presentation.PresentationActivity;

public class OneAdaptiveDesignActivity extends PresentationActivity {

    private OneAdaptiveDesignFragment oneAdaptiveDesignFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fm = getFragmentManager();
        oneAdaptiveDesignFragment = (OneAdaptiveDesignFragment) fm.findFragmentByTag(OneAdaptiveDesignFragment.TAG);

        if (oneAdaptiveDesignFragment == null) {
            oneAdaptiveDesignFragment = OneAdaptiveDesignFragment.newInstance();
            addFragment(oneAdaptiveDesignFragment, OneAdaptiveDesignFragment.TAG);
        }
    }
}