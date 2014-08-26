package com.wildsmith.material.presentation.uitopics;

import android.app.FragmentManager;
import android.os.Bundle;

import com.wildsmith.material.presentation.PresentationActivity;

public class AppEnvironmentActivity extends PresentationActivity {

    private AppEnvironmentFragment appEnvironmentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fm = getFragmentManager();
        appEnvironmentFragment = (AppEnvironmentFragment) fm.findFragmentByTag(AppEnvironmentFragment.TAG);

        if (appEnvironmentFragment == null) {
            appEnvironmentFragment = AppEnvironmentFragment.newInstance();
            addFragment(appEnvironmentFragment, AppEnvironmentFragment.TAG);
        }
    }
}