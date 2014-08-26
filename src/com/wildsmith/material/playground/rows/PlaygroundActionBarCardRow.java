package com.wildsmith.material.playground.rows;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.wildsmith.material.R;

public class PlaygroundActionBarCardRow extends PlaygroundCardListRow {

    @Override
    public View getView(LayoutInflater inflater, View contentView, Context context, int position) {
        ViewHolder holder = null;
        if (contentView == null) {
            contentView = inflater.inflate(R.layout.playground_action_bar_card_row, null);
            holder = createViewHolder(contentView, position);
        } else {
            Object contentViewHolder = contentView.getTag();
            if (contentViewHolder instanceof ViewHolder == false) {
                contentView = inflater.inflate(R.layout.playground_action_bar_card_row, null);
                holder = createViewHolder(contentView, position);
            } else {
                holder = (ViewHolder) contentViewHolder;
            }
        }

        if (holder == null) {
            return contentView;
        }

        return contentView;
    }

    @Override
    public View getTitleTextView(View view) {
        if (view == null) {
            return null;
        }

        return view.findViewById(R.id.elevation_title_text);
    }

    @Override
    public View getInfoTextView(View view) {
        if (view == null) {
            return null;
        }

        return view.findViewById(R.id.elevation_info_text);
    }

    private ViewHolder createViewHolder(View contentView, int position) {
        ViewHolder viewHolder = new ViewHolder();
        if (contentView == null) {
            return viewHolder;
        }

        viewHolder.actionBarView = contentView.findViewById(R.id.action_bar_view);
        // viewHolder.actionBarView.setViewName("card:view:" + position);

        contentView.setTag(viewHolder);

        return viewHolder;
    }

    private class ViewHolder {

        View actionBarView;
    }
}