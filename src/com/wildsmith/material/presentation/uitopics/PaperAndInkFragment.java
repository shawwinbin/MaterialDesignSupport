package com.wildsmith.material.presentation.uitopics;

import java.util.List;

import com.wildsmith.material.R;
import com.wildsmith.material.list.ListRow;
import com.wildsmith.material.presentation.PresentationListFragment;
import com.wildsmith.material.presentation.PresentationSlideFactory;
import com.wildsmith.material.utils.ResourceHelper;

public class PaperAndInkFragment extends PresentationListFragment {

    public static final String TAG = AppEnvironmentFragment.class.getSimpleName();

    public PaperAndInkFragment() {}

    public static PaperAndInkFragment newInstance() {
        PaperAndInkFragment fragment = new PaperAndInkFragment();
        return fragment;
    }

    @Override
    public void setupAdapterRows() {
        if (mAdapter == null || mListView == null) {
            return;
        }

        String[] paperAndInkClasses = ResourceHelper.getStringArray(R.array.paper_and_ink_classes);
        if (paperAndInkClasses == null || paperAndInkClasses.length == 0) {
            return;
        }

        String[] paperAndInkValues = ResourceHelper.getStringArray(R.array.paper_and_ink_values);
        if (paperAndInkValues == null || paperAndInkValues.length == 0) {
            return;
        }

        List<ListRow> rows = PresentationSlideFactory.newSlideDeck(paperAndInkClasses, paperAndInkValues, R.array.principles_classes);
        mAdapter.setRows(rows);
    }

    @Override
    public String getFragmentTag() {
        return TAG;
    }

    @Override
    public int getActionBarTitle() {
        return R.string.presentation_paper_and_ink_title;
    }

    @Override
    public boolean isListViewScrollable() {
        return false;
    }
}