package com.wildsmith.material.presentation.uitopics;

import java.util.List;

import com.wildsmith.material.R;
import com.wildsmith.material.list.ListRow;
import com.wildsmith.material.presentation.PresentationListFragment;
import com.wildsmith.material.presentation.PresentationSlideFactory;
import com.wildsmith.material.utils.ResourceHelper;

public class MeaningfulMotionFragment extends PresentationListFragment {

    public static final String TAG = AppEnvironmentFragment.class.getSimpleName();

    public MeaningfulMotionFragment() {}

    public static MeaningfulMotionFragment newInstance() {
        MeaningfulMotionFragment fragment = new MeaningfulMotionFragment();
        return fragment;
    }

    @Override
    public void setupAdapterRows() {
        if (mAdapter == null || mListView == null) {
            return;
        }

        String[] meaningfulMotionClasses = ResourceHelper.getStringArray(R.array.meaningful_motion_classes);
        if (meaningfulMotionClasses == null || meaningfulMotionClasses.length == 0) {
            return;
        }

        String[] meaningfulMotionValues = ResourceHelper.getStringArray(R.array.meaningful_motion_values);
        if (meaningfulMotionValues == null || meaningfulMotionValues.length == 0) {
            return;
        }

        List<ListRow> rows =
                PresentationSlideFactory.newSlideDeck(meaningfulMotionClasses, meaningfulMotionValues, R.array.principles_classes);
        mAdapter.setRows(rows);
    }

    @Override
    public String getFragmentTag() {
        return TAG;
    }

    @Override
    public int getActionBarTitle() {
        return R.string.presentation_meaningful_motion_title;
    }

    @Override
    public boolean isListViewScrollable() {
        return false;
    }
}