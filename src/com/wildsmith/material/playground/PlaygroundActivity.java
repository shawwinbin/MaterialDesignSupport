package com.wildsmith.material.playground;

import android.app.ActionBar;
import android.os.Bundle;

import com.wildsmith.material.R;
import com.wildsmith.material.core.CoreMaterialActivity;

public class PlaygroundActivity extends CoreMaterialActivity {

    private PlaygroundFragment playgroundFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getActionBar();
        actionBar.show();

        // getWindow().setAllowEnterTransitionOverlap(true);
        // getWindow().setAllowExitTransitionOverlap(true);

        playgroundFragment = new PlaygroundFragment();
        addFragment(playgroundFragment, PlaygroundFragment.TAG);
    }

    @Override
    protected int getNavigationResource() {
        return R.drawable.ic_drawer;
    }

    @Override
    protected boolean isActionBarNecessary() {
        return true;
    }
}