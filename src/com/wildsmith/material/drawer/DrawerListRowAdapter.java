package com.wildsmith.material.drawer;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wildsmith.material.list.ListRow;
import com.wildsmith.material.list.expandable.AnimatedExpandableListAdapter;
import com.wildsmith.material.list.expandable.AnimatedExpandableListView;
import com.wildsmith.material.list.expandable.ExpandableListRow;

public class DrawerListRowAdapter extends AnimatedExpandableListAdapter {

    private static final String TAG = DrawerListRowAdapter.class.getSimpleName();

    private List<ListRow> mRows;

    private LayoutInflater mInflater;

    protected Context mContext;

    public DrawerListRowAdapter(AnimatedExpandableListView expandableListView, Context pContext) {
        super(expandableListView);

        mInflater = LayoutInflater.from(pContext);
        mContext = pContext;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ListRow mRow = getGroup(groupPosition);
        if (mRow == null) {
            return null;
        }

        try {
            View view = mRow.getView(mInflater, convertView, mContext, groupPosition);
            return view;
        } catch (Exception e) {
            Log.e(TAG, "Could not create the given row for group position: " + groupPosition + "." + e.getMessage());
        }

        return null;
    }

    @Override
    public View getRealChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ListRow mRow = getChild(groupPosition, childPosition);
        if (mRow == null) {
            return null;
        }

        try {
            View view = mRow.getView(mInflater, convertView, mContext, childPosition);
            return view;
        } catch (Exception e) {
            Log.e(TAG, "Could not create the given row for group position: " + groupPosition + "." + e.getMessage());
        }

        return null;
    }

    @Override
    public ListRow getChild(int groupPosition, int childPosition) {
        ListRow listRow = getGroup(groupPosition);
        if (listRow == null || listRow instanceof ExpandableListRow == false) {
            return null;
        }

        return ((ExpandableListRow) listRow).getChild(childPosition);
    }

    @Override
    public int getRealChildrenCount(int groupPosition) {
        ListRow listRow = getGroup(groupPosition);
        if (listRow == null || listRow instanceof ExpandableListRow == false) {
            return -1;
        }

        return ((ExpandableListRow) listRow).getChildrenCount();
    }

    @Override
    public ListRow getGroup(int groupPosition) {
        if (mRows == null || mRows.isEmpty() || mRows.size() < groupPosition) {
            return null;
        }

        return mRows.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        if (mRows == null || mRows.isEmpty()) {
            return 0;
        }

        return mRows.size();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int arg0, int arg1) {
        return false;
    }

    public List<ListRow> getRows() {
        return mRows;
    }

    public void setRows(List<ListRow> rows) {
        mRows = rows;
    }
}