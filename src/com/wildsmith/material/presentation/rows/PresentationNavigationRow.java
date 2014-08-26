package com.wildsmith.material.presentation.rows;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.wildsmith.material.R;
import com.wildsmith.material.core.CoreMaterialActivity;
import com.wildsmith.material.drawer.DrawerOnClickListener;
import com.wildsmith.material.views.FloatingActionButton;

public class PresentationNavigationRow extends PresentationListRow {

    private CoreMaterialActivity mActivity;

    private String mBackwardNavigationIndex;

    private String mBackwardNavigationTitle;

    private String mForwardNavigationIndex;

    private String mForwardNavigationTitle;

    private int mResId;

    @Override
    public void setArguments(Object[] objects) {
        mBackwardNavigationIndex = (String) objects[0];
        mBackwardNavigationTitle = (String) objects[1];
        mForwardNavigationIndex = (String) objects[2];
        mForwardNavigationTitle = (String) objects[3];
    }

    @Override
    public void setAdditionalArguments(CoreMaterialActivity activity, ListView listView, FloatingActionButton fabButton, boolean isLast) {
        mActivity = activity;
    }

    public void setNavigationResourceId(int resId) {
        mResId = resId;
    }

    @Override
    public View getView(LayoutInflater inflater, View contentView, Context context, final int position) {
        ViewHolder holder = null;
        if (contentView == null) {
            contentView = inflater.inflate(R.layout.presentation_navigation_row, null);
            holder = createViewHolder(contentView);
        } else {
            Object contentViewHolder = contentView.getTag();
            if (contentViewHolder instanceof ViewHolder == false) {
                contentView = inflater.inflate(R.layout.presentation_navigation_row, null);
                holder = createViewHolder(contentView);
            } else {
                holder = (ViewHolder) contentViewHolder;
            }
        }

        if (holder == null) {
            return contentView;
        }

        setupNavigationBackButtonText(holder);
        setupNavigationBackButton(position, holder);
        setupNavigationForwardButtonText(holder);
        setupNavigationForwardButton(position, holder);

        return contentView;
    }

    private void setupNavigationBackButtonText(ViewHolder holder) {
        if (holder.navigationBack == null || holder.navigationBackImage == null) {
            return;
        }

        if (mBackwardNavigationTitle == null || "".equals(mBackwardNavigationTitle)) {
            holder.navigationBack.setVisibility(View.INVISIBLE);
            holder.navigationBackImage.setVisibility(View.INVISIBLE);
            return;
        }

        holder.navigationBack.setText(mBackwardNavigationTitle);
    }

    private void setupNavigationBackButton(final int position, ViewHolder holder) {
        if (holder.navigationBack == null) {
            return;
        }

        if (mBackwardNavigationTitle == null || "".equals(mBackwardNavigationTitle)) {
            holder.navigationBack.setOnClickListener(null);
            return;
        }

        holder.navigationBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mBackwardNavigationIndex == null || "".equals(mBackwardNavigationIndex)) {
                    return;
                }

                final int backwardNavigationIndex = Integer.valueOf(mBackwardNavigationIndex);

                DrawerOnClickListener clickListener = mActivity.createNewChildDrawerOnClickListener(mResId, position);
                clickListener.selectItem(backwardNavigationIndex);
            }
        });
    }

    private void setupNavigationForwardButtonText(ViewHolder holder) {
        if (holder.navigationForward == null || holder.navigationForwardImage == null) {
            return;
        }

        if (mForwardNavigationTitle == null || "".equals(mForwardNavigationTitle)) {
            holder.navigationForward.setVisibility(View.INVISIBLE);
            holder.navigationForwardImage.setVisibility(View.INVISIBLE);
            return;
        }

        holder.navigationForward.setText(mForwardNavigationTitle);
    }

    private void setupNavigationForwardButton(final int position, ViewHolder holder) {
        if (holder.navigationForward == null) {
            return;
        }

        if (mForwardNavigationTitle == null || "".equals(mForwardNavigationTitle)) {
            holder.navigationForward.setOnClickListener(null);
            return;
        }

        holder.navigationForward.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mForwardNavigationIndex == null || "".equals(mForwardNavigationIndex)) {
                    return;
                }

                final int forwardNavigationIndex = Integer.valueOf(mForwardNavigationIndex);

                DrawerOnClickListener clickListener = mActivity.createNewChildDrawerOnClickListener(mResId, position);
                clickListener.selectItem(forwardNavigationIndex);
            }
        });
    }

    @Override
    protected ViewHolder createViewHolder(View contentView) {
        ViewHolder viewHolder = new ViewHolder();
        if (contentView == null) {
            return viewHolder;
        }

        viewHolder.navigationBackImage = (ImageView) contentView.findViewById(R.id.presentation_navigation_back_image);
        viewHolder.navigationBack = (Button) contentView.findViewById(R.id.presentation_navigation_back);
        viewHolder.navigationForwardImage = (ImageView) contentView.findViewById(R.id.presentation_navigation_forward_image);
        viewHolder.navigationForward = (Button) contentView.findViewById(R.id.presentation_navigation_forward);

        contentView.setTag(viewHolder);

        return viewHolder;
    }

    private class ViewHolder extends PresentationViewHolder {

        ImageView navigationBackImage;

        Button navigationBack;

        ImageView navigationForwardImage;

        Button navigationForward;
    }
}