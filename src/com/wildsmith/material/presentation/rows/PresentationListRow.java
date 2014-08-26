package com.wildsmith.material.presentation.rows;

import android.app.Activity;
import android.content.res.Configuration;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;

import com.wildsmith.material.R;
import com.wildsmith.material.core.CoreMaterialActivity;
import com.wildsmith.material.list.ListRow;
import com.wildsmith.material.utils.ResourceHelper;
import com.wildsmith.material.views.FloatingActionButton;

public abstract class PresentationListRow extends ListRow {

    protected Activity mActivity;

    private ListView mListView;

    private FloatingActionButton mFabButton;

    private Boolean mIsLast;

    public abstract void setArguments(Object[] objects);

    public void setAdditionalArguments(CoreMaterialActivity activity, ListView listView, FloatingActionButton fabButton, boolean isLast) {
        mActivity = activity;
        mListView = listView;
        mFabButton = fabButton;
        mIsLast = isLast;
    }

    protected abstract PresentationViewHolder createViewHolder(View contentView);

    protected void setupSlideTopSize(int position, PresentationViewHolder holder) {
        if (holder.slideTop == null || holder.divider == null) {
            return;
        }

        int actionBarHeight = ResourceHelper.getActionBarHeight();
        final int oldActionBarHeight = actionBarHeight;

        if (position == 2) {
            actionBarHeight = (actionBarHeight * 2);
        }

        final int orentation = ResourceHelper.getScreenOrientation();
        int height = 0;
        if (orentation == Configuration.ORIENTATION_PORTRAIT) {
            height = getHeightForPortrait(holder, actionBarHeight, oldActionBarHeight);
        } else {
            height = getHeightForLandscape(holder, actionBarHeight, oldActionBarHeight);
        }

        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, height);
        holder.slideTop.setLayoutParams(layoutParams);
    }

    private int getHeightForPortrait(PresentationViewHolder holder, int actionBarHeight, final int oldActionBarHeight) {
        int height = 0;
        if (mIsLast == false) {
            height = mListView.getHeight() - actionBarHeight - oldActionBarHeight;
            holder.divider.setVisibility(View.VISIBLE);
        } else {
            height = mListView.getHeight() - (actionBarHeight * 2) - oldActionBarHeight;
            holder.divider.setVisibility(View.GONE);
        }

        return height;
    }

    private int getHeightForLandscape(PresentationViewHolder holder, int actionBarHeight, final int oldActionBarHeight) {
        int height = 0;
        if (mIsLast == false) {
            height = mListView.getWidth() - actionBarHeight - oldActionBarHeight;
            holder.divider.setVisibility(View.VISIBLE);
        } else {
            height = mListView.getWidth() - (actionBarHeight * 2) - oldActionBarHeight;
            holder.divider.setVisibility(View.GONE);
        }

        return height;
    }

    protected void setupFABButtonPosition(PresentationViewHolder holder) {
        if (mFabButton == null || mFabButton.isAttached() || holder.fabGrip == null) {
            return;
        }

        mFabButton.setY(holder.slideTop.getLayoutParams().height + (ResourceHelper.getActionBarHeight() * 2)
                - ResourceHelper.getDimension(R.dimen.half_fab_size));
        mFabButton.setAttached(true);
    }

    protected void createPresentationViewHolder(PresentationViewHolder viewHolder, View contentView) {
        if (contentView == null) {
            return;
        }

        viewHolder.fabGrip = contentView.findViewById(R.id.presentation_fab_grip);
        viewHolder.slideTop = contentView.findViewById(R.id.presentation_slide_top);
        viewHolder.divider = contentView.findViewById(R.id.presentation_divider);
    }

    protected class PresentationViewHolder {

        private View fabGrip;

        private View slideTop;

        private View divider;
    }
}