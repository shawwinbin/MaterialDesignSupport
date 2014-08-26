package com.wildsmith.material.presentation.rows;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.wildsmith.material.R;
import com.wildsmith.material.core.CoreMaterialActivity;
import com.wildsmith.material.views.FloatingActionButton;

public class PresentationActionBarRow extends PresentationListRow {

    private String mActionBarTitle;

    @Override
    public void setArguments(Object[] objects) {
        mActionBarTitle = (String) objects[0];
    }

    @Override
    public void setAdditionalArguments(CoreMaterialActivity activity, ListView listView, FloatingActionButton fabButton, boolean isLast) {}

    @Override
    public View getView(LayoutInflater inflater, View contentView, Context context, int position) {
        ViewHolder holder = null;
        if (contentView == null) {
            contentView = inflater.inflate(R.layout.presentation_action_bar_row, null);
            holder = createViewHolder(contentView);
        } else {
            Object contentViewHolder = contentView.getTag();
            if (contentViewHolder instanceof ViewHolder == false) {
                contentView = inflater.inflate(R.layout.presentation_action_bar_row, null);
                holder = createViewHolder(contentView);
            } else {
                holder = (ViewHolder) contentViewHolder;
            }
        }

        if (holder == null) {
            return contentView;
        }

        setupActionBarTitleText(holder);

        return contentView;
    }

    private void setupActionBarTitleText(ViewHolder holder) {
        if (holder.actionBarTextView == null) {
            return;
        }

        holder.actionBarTextView.setText(mActionBarTitle);
    }

    @Override
    protected ViewHolder createViewHolder(View contentView) {
        ViewHolder viewHolder = new ViewHolder();
        if (contentView == null) {
            return viewHolder;
        }

        viewHolder.actionBarTextView = (TextView) contentView.findViewById(R.id.presentation_action_bar_title);

        contentView.setTag(viewHolder);

        return viewHolder;
    }

    private class ViewHolder extends PresentationViewHolder {

        TextView actionBarTextView;
    }
}