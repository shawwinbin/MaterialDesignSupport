package com.wildsmith.material.presentation.uitopics;

import java.util.List;

import com.wildsmith.material.R;
import com.wildsmith.material.list.ListRow;
import com.wildsmith.material.playground.PlaygroundFragment;
import com.wildsmith.material.presentation.PresentationListFragment;
import com.wildsmith.material.presentation.PresentationSlideFactory;
import com.wildsmith.material.utils.ResourceHelper;

public class WhatIsMaterialFragment extends PresentationListFragment {

    public static final String TAG = PlaygroundFragment.class.getSimpleName();

    public WhatIsMaterialFragment() {}

    public static WhatIsMaterialFragment newInstance() {
        WhatIsMaterialFragment fragment = new WhatIsMaterialFragment();
        return fragment;
    }

    @Override
    public void setupAdapterRows() {
        if (mAdapter == null || mListView == null) {
            return;
        }

        String[] materialClasses = ResourceHelper.getStringArray(R.array.what_is_material_classes);
        if (materialClasses == null || materialClasses.length == 0) {
            return;
        }

        String[] materialInfo = ResourceHelper.getStringArray(R.array.what_is_material_values);
        if (materialInfo == null || materialInfo.length == 0) {
            return;
        }

        List<ListRow> rows = PresentationSlideFactory.newSlideDeck(materialClasses, materialInfo, R.array.principles_classes);
        mAdapter.setRows(rows);
    }

    @Override
    public String getFragmentTag() {
        return TAG;
    }

    @Override
    public int getActionBarTitle() {
        return R.string.presentation_what_is_material_title;
    }

    @Override
    public boolean isListViewScrollable() {
        return false;
    }
}