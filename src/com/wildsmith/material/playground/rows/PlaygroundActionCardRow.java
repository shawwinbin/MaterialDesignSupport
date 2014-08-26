package com.wildsmith.material.playground.rows;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;

import com.wildsmith.material.R;

public class PlaygroundActionCardRow extends PlaygroundCardListRow {

    @Override
    public View getView(LayoutInflater inflater, View contentView, Context context, int position) {
        ViewHolder holder = null;
        if (contentView == null) {
            contentView = inflater.inflate(R.layout.playground_action_card_row, null);
            holder = createViewHolder(contentView, position);
        } else {
            Object contentViewHolder = contentView.getTag();
            if (contentViewHolder instanceof ViewHolder == false) {
                contentView = inflater.inflate(R.layout.playground_action_card_row, null);
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

        return view.findViewById(R.id.action_title_text);
    }

    @Override
    public View getInfoTextView(View view) {
        if (view == null) {
            return null;
        }

        return view.findViewById(R.id.action_edit_text);
    }

    private ViewHolder createViewHolder(View contentView, int position) {
        ViewHolder viewHolder = new ViewHolder();
        if (contentView == null) {
            return viewHolder;
        }

        viewHolder.cardView = (CardView) contentView.findViewById(R.id.card_view);
        // viewHolder.cardView.setViewName("card:view:" + position);

        contentView.setTag(viewHolder);

        return viewHolder;
    }

    private class ViewHolder {

        CardView cardView;
    }
}