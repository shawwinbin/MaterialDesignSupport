package com.wildsmith.material.presentation.uitopics;

import android.app.FragmentManager;
import android.os.Bundle;

import com.wildsmith.material.presentation.PresentationActivity;

public class WhatIsMaterialActivity extends PresentationActivity {

    private WhatIsMaterialFragment whatIsMaterialFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fm = getFragmentManager();
        whatIsMaterialFragment = (WhatIsMaterialFragment) fm.findFragmentByTag(WhatIsMaterialFragment.TAG);

        if (whatIsMaterialFragment == null) {
            whatIsMaterialFragment = WhatIsMaterialFragment.newInstance();
            addFragment(whatIsMaterialFragment, WhatIsMaterialFragment.TAG);
        }
    }
}