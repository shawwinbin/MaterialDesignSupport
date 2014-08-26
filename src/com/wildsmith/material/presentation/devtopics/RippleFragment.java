package com.wildsmith.material.presentation.devtopics;

import java.util.List;

import com.wildsmith.material.R;
import com.wildsmith.material.list.ListRow;
import com.wildsmith.material.presentation.PresentationListFragment;
import com.wildsmith.material.presentation.PresentationSlideFactory;
import com.wildsmith.material.utils.ResourceHelper;

public class RippleFragment extends PresentationListFragment {

    public static final String TAG = RippleFragment.class.getSimpleName();

    public RippleFragment() {}

    public static RippleFragment newInstance() {
        RippleFragment fragment = new RippleFragment();
        return fragment;
    }

    @Override
    public void setupAdapterRows() {
        if (mAdapter == null || mListView == null) {
            return;
        }

        String[] appEnvironmentClasses = ResourceHelper.getStringArray(R.array.ripple_classes);
        if (appEnvironmentClasses == null || appEnvironmentClasses.length == 0) {
            return;
        }

        String[] appEnvironmentInfo = ResourceHelper.getStringArray(R.array.ripple_values);
        if (appEnvironmentInfo == null || appEnvironmentInfo.length == 0) {
            return;
        }

        List<ListRow> rows =
                PresentationSlideFactory.newSlideDeck(appEnvironmentClasses, appEnvironmentInfo, R.array.development_concepts_classes);
        mAdapter.setRows(rows);
    }

    @Override
    public String getFragmentTag() {
        return TAG;
    }

    @Override
    public int getActionBarTitle() {
        return R.string.dev_ripple_title;
    }

    @Override
    public boolean isListViewScrollable() {
        return false;
    }
}