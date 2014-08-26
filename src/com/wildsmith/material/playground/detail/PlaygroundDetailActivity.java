package com.wildsmith.material.playground.detail;

import android.os.Bundle;
import android.view.MenuItem;

import com.wildsmith.material.R;
import com.wildsmith.material.core.CoreMaterialActivity;

public class PlaygroundDetailActivity extends CoreMaterialActivity {

    private PlaygroundDetailFragment detailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // getWindow().setAllowEnterTransitionOverlap(true);
        // getWindow().setAllowExitTransitionOverlap(true);

        detailFragment = PlaygroundDetailFragment.newInstance(mActionBarShowing);
        addFragment(detailFragment, PlaygroundDetailFragment.TAG);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // finishAfterTransition();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getNavigationResource() {
        return R.drawable.ic_action_back_white;
    }

    @Override
    protected boolean isActionBarNecessary() {
        return true;
    }
}