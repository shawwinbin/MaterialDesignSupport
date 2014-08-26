package com.wildsmith.material.presentation.rows;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.wildsmith.material.R;

public class PresentationInformationRow extends PresentationListRow {

    private String mInformationTitle;

    private String mInformationMessage;

    @Override
    public void setArguments(Object[] objects) {
        mInformationTitle = (String) objects[0];
        mInformationMessage = (String) objects[1];
    }

    @Override
    public View getView(LayoutInflater inflater, View contentView, Context context, int position) {
        ViewHolder holder = null;
        if (contentView == null) {
            contentView = inflater.inflate(R.layout.presentation_information_row, null);
            holder = createViewHolder(contentView);
        } else {
            Object contentViewHolder = contentView.getTag();
            if (contentViewHolder instanceof ViewHolder == false) {
                contentView = inflater.inflate(R.layout.presentation_information_row, null);
                holder = createViewHolder(contentView);
            } else {
                holder = (ViewHolder) contentViewHolder;
            }
        }

        if (holder == null) {
            return contentView;
        }

        setupTitleText(holder);
        setupInformationText(holder);
        setupSlideTopSize(position, holder);
        setupFABButtonPosition(holder);

        return contentView;
    }

    private void setupTitleText(ViewHolder holder) {
        if (holder.informationTitleTextView == null) {
            return;
        }

        holder.informationTitleTextView.setText(mInformationTitle);
    }

    private void setupInformationText(ViewHolder holder) {
        if (holder.informationMessageTextView == null) {
            return;
        }

        holder.informationMessageTextView.setText(mInformationMessage);
    }

    @Override
    protected ViewHolder createViewHolder(View contentView) {
        ViewHolder viewHolder = new ViewHolder();
        if (contentView == null) {
            return viewHolder;
        }

        createPresentationViewHolder(viewHolder, contentView);

        viewHolder.informationTitleTextView = (TextView) contentView.findViewById(R.id.presentation_information_title);
        viewHolder.informationMessageTextView = (TextView) contentView.findViewById(R.id.presentation_information_message);

        contentView.setTag(viewHolder);

        return viewHolder;
    }

    private class ViewHolder extends PresentationViewHolder {

        TextView informationTitleTextView;

        TextView informationMessageTextView;
    }
}