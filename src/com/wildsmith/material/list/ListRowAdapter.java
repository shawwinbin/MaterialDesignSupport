package com.wildsmith.material.list;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ListRowAdapter extends ArrayAdapter<ListRow> {

    private static final String TAG = ListRowAdapter.class.getSimpleName();

    protected List<ListRow> mRows;

    protected LayoutInflater mInflater;

    protected Context mContext;

    public ListRowAdapter(Context pContext, int pViewResourceId) {
        super(pContext, pViewResourceId);

        mInflater = LayoutInflater.from(pContext);
        mContext = pContext;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListRow mRow = getRow(position);
        if (mRow == null) {
            return null;
        }

        try {
            View view = mRow.getView(mInflater, convertView, mContext, position);
            return view;
        } catch (Exception e) {
            Log.e(TAG, "Could not create the given row for position: " + position + "." + e.getMessage());
        }

        return null;
    }

    public List<ListRow> getRows() {
        return mRows;
    }

    public void setRows(List<ListRow> rows) {
        mRows = rows;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        if (mRows == null || mRows.isEmpty()) {
            return 0;
        }

        return mRows.size();
    }

    public ListRow getRow(int position) {
        if (mRows == null || mRows.isEmpty() || mRows.size() < position) {
            return null;
        }

        return mRows.get(position);
    }
}