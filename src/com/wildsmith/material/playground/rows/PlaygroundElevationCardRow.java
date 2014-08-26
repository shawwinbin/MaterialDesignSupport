package com.wildsmith.material.playground.rows;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;

import com.wildsmith.material.R;

public class PlaygroundElevationCardRow extends PlaygroundCardListRow {

    @Override
    public View getView(LayoutInflater inflater, View contentView, Context context, int position) {
        ViewHolder holder = null;
        if (contentView == null) {
            contentView = inflater.inflate(R.layout.playground_elevation_card_row, null);
            holder = createViewHolder(contentView, position);
        } else {
            Object contentViewHolder = contentView.getTag();
            if (contentViewHolder instanceof ViewHolder == false) {
                contentView = inflater.inflate(R.layout.playground_elevation_card_row, null);
                holder = createViewHolder(contentView, position);
            } else {
                holder = (ViewHolder) contentViewHolder;
            }
        }

        if (holder == null) {
            return contentView;
        }

        setupCardViewListener(holder);

        return contentView;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupCardViewListener(final ViewHolder holder) {
        if (holder.cardView == null) {
            return;
        }

        holder.cardView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getActionMasked();
                /* Raise view on ACTION_DOWN and lower it on ACTION_UP. */
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // view.setTranslationZ(20);
                        break;
                    case MotionEvent.ACTION_UP:
                        // view.setTranslationZ(0);
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });
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

        viewHolder.cardView = (CardView) contentView.findViewById(R.id.card_view);
        // viewHolder.cardView.setViewName("card:view:" + position);

        contentView.setTag(viewHolder);

        return viewHolder;
    }

    private class ViewHolder {

        CardView cardView;
    }
}