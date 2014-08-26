package com.wildsmith.material.presentation.rows;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.wildsmith.material.R;

public class PresentationTitleRow extends PresentationListRow {

    private String mTitle;

    @Override
    public void setArguments(Object[] objects) {
        mTitle = (String) objects[0];
    }

    @Override
    public View getView(LayoutInflater inflater, View contentView, Context context, int position) {
        ViewHolder holder = null;
        if (contentView == null) {
            contentView = inflater.inflate(R.layout.presentation_title_row, null);
            holder = createViewHolder(contentView);
        } else {
            Object contentViewHolder = contentView.getTag();
            if (contentViewHolder instanceof ViewHolder == false) {
                contentView = inflater.inflate(R.layout.presentation_title_row, null);
                holder = createViewHolder(contentView);
            } else {
                holder = (ViewHolder) contentViewHolder;
            }
        }

        if (holder == null) {
            return contentView;
        }

        setupTitleText(holder);
        setupSlideTopSize(position, holder);
        setupFABButtonPosition(holder);

        return contentView;
    }

    private void setupTitleText(ViewHolder holder) {
        if (holder.titleTextView == null) {
            return;
        }

        holder.titleTextView.setText(mTitle);
    }

    @Override
    protected ViewHolder createViewHolder(View contentView) {
        ViewHolder viewHolder = new ViewHolder();
        if (contentView == null) {
            return viewHolder;
        }

        createPresentationViewHolder(viewHolder, contentView);

        viewHolder.titleTextView = (TextView) contentView.findViewById(R.id.presentation_title);

        contentView.setTag(viewHolder);

        return viewHolder;
    }

    private class ViewHolder extends PresentationViewHolder {

        TextView titleTextView;
    }
}