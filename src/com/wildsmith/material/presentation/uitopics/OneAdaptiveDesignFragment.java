package com.wildsmith.material.presentation.uitopics;

import java.util.List;

import com.wildsmith.material.R;
import com.wildsmith.material.list.ListRow;
import com.wildsmith.material.presentation.PresentationListFragment;
import com.wildsmith.material.presentation.PresentationSlideFactory;
import com.wildsmith.material.utils.ResourceHelper;

public class OneAdaptiveDesignFragment extends PresentationListFragment {

    public static final String TAG = OneAdaptiveDesignFragment.class.getSimpleName();

    public OneAdaptiveDesignFragment() {}

    public static OneAdaptiveDesignFragment newInstance() {
        OneAdaptiveDesignFragment fragment = new OneAdaptiveDesignFragment();
        return fragment;
    }

    @Override
    public void setupAdapterRows() {
        if (mAdapter == null || mListView == null) {
            return;
        }

        String[] appEnvironmentClasses = ResourceHelper.getStringArray(R.array.one_adaptive_design_classes);
        if (appEnvironmentClasses == null || appEnvironmentClasses.length == 0) {
            return;
        }

        String[] appEnvironmentInfo = ResourceHelper.getStringArray(R.array.one_adaptive_design_values);
        if (appEnvironmentInfo == null || appEnvironmentInfo.length == 0) {
            return;
        }

        List<ListRow> rows = PresentationSlideFactory.newSlideDeck(appEnvironmentClasses, appEnvironmentInfo, R.array.principles_classes);
        mAdapter.setRows(rows);
    }

    @Override
    public String getFragmentTag() {
        return TAG;
    }

    @Override
    public int getActionBarTitle() {
        return R.string.presentation_one_adaptive_design_title;
    }

    @Override
    public boolean isListViewScrollable() {
        return false;
    }
}