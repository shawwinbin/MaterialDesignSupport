package com.wildsmith.material.drawer.rows;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wildsmith.material.R;
import com.wildsmith.material.core.CoreMaterialActivity;
import com.wildsmith.material.core.CoreMaterialApplication;
import com.wildsmith.material.list.ListRow;

public class ChildNavigationDrawerRow extends ListRow {

    private CoreMaterialActivity mActivity;

    private int mClassesId;

    private String mChildRowText;

    public ChildNavigationDrawerRow(CoreMaterialActivity activity, int classesId, String childRowText) {
        mActivity = activity;
        mClassesId = classesId;
        mChildRowText = childRowText;
    }

    @Override
    public View getView(LayoutInflater inflater, View contentView, Context context, int position) {
        ViewHolder holder = null;
        if (contentView == null) {
            contentView =
                    inflater.inflate(R.layout.drawer_child_navigation_row, new LinearLayout(CoreMaterialApplication.getContext()), false);
            holder = createViewHolder(contentView);
        } else {
            Object contentViewHolder = contentView.getTag();
            if (contentViewHolder instanceof ViewHolder == false) {
                contentView =
                        inflater.inflate(R.layout.drawer_child_navigation_row, new LinearLayout(CoreMaterialApplication.getContext()),
                                false);
                holder = createViewHolder(contentView);
            } else {
                holder = (ViewHolder) contentViewHolder;
            }
        }

        if (holder == null) {
            return contentView;
        }

        if (holder.text != null) {
            holder.text.setText(mChildRowText);
            holder.text.setOnClickListener(mActivity.createNewChildDrawerOnClickListener(mClassesId, position));
        }

        return contentView;
    }

    private ViewHolder createViewHolder(View contentView) {
        ViewHolder viewHolder = new ViewHolder();
        if (contentView == null) {
            return viewHolder;
        }

        viewHolder.text = (TextView) contentView.findViewById(R.id.text);

        contentView.setTag(viewHolder);

        return viewHolder;
    }

    private class ViewHolder {

        TextView text;
    }
}