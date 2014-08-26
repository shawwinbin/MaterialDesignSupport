package com.wildsmith.material.presentation;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.wildsmith.material.core.CoreMaterialActivity;
import com.wildsmith.material.list.ListRow;
import com.wildsmith.material.list.ListRowAdapter;
import com.wildsmith.material.presentation.rows.PresentationActionBarRow;
import com.wildsmith.material.presentation.rows.PresentationImageRow;
import com.wildsmith.material.presentation.rows.PresentationInformationRow;
import com.wildsmith.material.presentation.rows.PresentationListRow;
import com.wildsmith.material.presentation.rows.PresentationNavigationRow;
import com.wildsmith.material.presentation.rows.PresentationTitleRow;
import com.wildsmith.material.presentation.rows.PresentationVideoRow;
import com.wildsmith.material.views.FloatingActionButton;

public class PresentationListRowAdapter extends ListRowAdapter {

    private CoreMaterialActivity mActivity;

    private ListView mListView;

    private FloatingActionButton mFabButton;

    public PresentationListRowAdapter(Context pContext, int pViewResourceId) {
        super(pContext, pViewResourceId);
    }

    public void setArguments(CoreMaterialActivity activity, ListView listView, FloatingActionButton fabButton) {
        mActivity = activity;
        mListView = listView;
        mFabButton = fabButton;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PresentationListRow mRow = (PresentationListRow) getRow(position);
        if (mRow == null) {
            return null;
        }

        if (position == getCount() - 2) {
            mRow.setAdditionalArguments(mActivity, mListView, mFabButton, true);
        } else {
            mRow.setAdditionalArguments(mActivity, mListView, mFabButton, false);
        }

        return super.getView(position, convertView, parent);
    }

    @Override
    public int getViewTypeCount() {
        return 6;
    }

    @Override
    public int getItemViewType(int position) {
        if (mRows == null || mRows.isEmpty() || mRows.size() < position) {
            return -1;
        }

        ListRow row = mRows.get(position);
        if (row instanceof PresentationActionBarRow) {
            return 0;
        } else if (row instanceof PresentationInformationRow) {
            return 1;
        } else if (row instanceof PresentationVideoRow) {
            return 2;
        } else if (row instanceof PresentationImageRow) {
            return 3;
        } else if (row instanceof PresentationTitleRow) {
            return 4;
        } else if (row instanceof PresentationNavigationRow) {
            return 5;
        }

        return -1;
    }
}