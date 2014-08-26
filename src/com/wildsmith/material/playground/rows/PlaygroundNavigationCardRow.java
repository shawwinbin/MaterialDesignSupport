package com.wildsmith.material.playground.rows;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.wildsmith.material.R;
import com.wildsmith.material.playground.detail.PlaygroundDetailActivity;

public class PlaygroundNavigationCardRow extends PlaygroundCardListRow {

    @Override
    public View getView(LayoutInflater inflater, View contentView, Context context, int position) {
        ViewHolder holder = null;
        if (contentView == null) {
            contentView = inflater.inflate(R.layout.playground_navigation_card_row, null);
            holder = createViewHolder(contentView, position);
        } else {
            Object contentViewHolder = contentView.getTag();
            if (contentViewHolder instanceof ViewHolder == false) {
                contentView = inflater.inflate(R.layout.playground_navigation_card_row, null);
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

    private void setupCardViewListener(final ViewHolder holder) {
        if (holder.cardView == null) {
            return;
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {

            // @SuppressWarnings("unchecked")
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mActivity, PlaygroundDetailActivity.class);
                intent.putExtra("ActionBarShowing", mActivity.getActionBar().isShowing());

                // final View titleText = getTitleTextView(view);
                // final String titleViewName =
                // ResourceHelper.getResString(R.string.detail_title_view_name);
                //
                // final View infoText = getInfoTextView(view);
                // final String infoViewName =
                // ResourceHelper.getResString(R.string.detail_info_view_name);

                // ActivityOptions activityOptions =
                // ActivityOptions.makeSceneTransitionAnimation(mActivity, new Pair<View,
                // String>(titleText, titleViewName),
                // new Pair<View, String>(infoText, infoViewName));

                // mActivity.startActivity(intent, activityOptions.toBundle());
                mActivity.startActivity(intent);
            }
        });
    }

    @Override
    public View getTitleTextView(View view) {
        if (view == null) {
            return null;
        }

        return view.findViewById(R.id.navigation_title_text);
    }

    @Override
    public View getInfoTextView(View view) {
        if (view == null) {
            return null;
        }

        return view.findViewById(R.id.navigation_info_text);
    }

    private ViewHolder createViewHolder(View contentView, int position) {
        ViewHolder viewHolder = new ViewHolder();
        if (contentView == null) {
            return viewHolder;
        }

        viewHolder.cardView = (CardView) contentView.findViewById(R.id.card_view);
        // viewHolder.cardView.setViewName("card:view:" + position);
        viewHolder.titleText = (TextView) contentView.findViewById(R.id.navigation_title_text);
        // viewHolder.titleText.setViewName("nav:title:" + position);
        viewHolder.infoText = (TextView) contentView.findViewById(R.id.navigation_info_text);
        // viewHolder.infoText.setViewName("nav:info:" + position);

        contentView.setTag(viewHolder);

        return viewHolder;
    }

    private class ViewHolder {

        CardView cardView;

        TextView titleText;

        TextView infoText;
    }
}