package com.wildsmith.material.drawer.rows;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.wildsmith.material.R;
import com.wildsmith.material.core.CoreMaterialApplication;
import com.wildsmith.material.list.ListRow;

public class DividerDrawerRow extends ListRow {

    @Override
    public View getView(LayoutInflater inflater, View contentView, Context context, int position) {
        ViewHolder holder = null;
        if (contentView == null) {
            contentView = inflater.inflate(R.layout.drawer_divider_row, new LinearLayout(CoreMaterialApplication.getContext()), false);
            holder = createViewHolder(contentView);
        } else {
            Object contentViewHolder = contentView.getTag();
            if (contentViewHolder instanceof ViewHolder == false) {
                contentView = inflater.inflate(R.layout.drawer_divider_row, new LinearLayout(CoreMaterialApplication.getContext()), false);
                holder = createViewHolder(contentView);
            } else {
                holder = (ViewHolder) contentViewHolder;
            }
        }

        if (holder == null) {
            return contentView;
        }

        return contentView;
    }

    private ViewHolder createViewHolder(View contentView) {
        ViewHolder viewHolder = new ViewHolder();
        if (contentView == null) {
            return viewHolder;
        }

        contentView.setTag(viewHolder);

        return viewHolder;
    }

    private class ViewHolder {

    }
}