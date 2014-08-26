package com.wildsmith.material.presentation.rows;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.wildsmith.material.R;
import com.wildsmith.material.views.VideoViewRecycler;
import com.wildsmith.material.views.VideoViewRecycler.VideoViewRecyclerListener;

public class PresentationVideoRow extends PresentationListRow implements VideoViewRecyclerListener {

    private String mResourceName;

    private VideoViewRecycler mVideoView;

    private LinearLayout mVideoViewLoading;

    @Override
    public void setArguments(Object[] objects) {
        mResourceName = (String) objects[0];
    }

    @Override
    public View getView(LayoutInflater inflater, View contentView, Context context, int position) {
        ViewHolder holder = null;
        if (contentView == null) {
            contentView = inflater.inflate(R.layout.presentation_video_row, null);
            holder = createViewHolder(contentView);
        } else {
            Object contentViewHolder = contentView.getTag();
            if (contentViewHolder instanceof ViewHolder == false) {
                contentView = inflater.inflate(R.layout.presentation_video_row, null);
                holder = createViewHolder(contentView);
            } else {
                holder = (ViewHolder) contentViewHolder;
            }
        }

        if (holder == null) {
            return contentView;
        }

        mVideoView = holder.videoView;
        mVideoViewLoading = holder.videoViewLoading;

        setupVideoView(holder);
        setupVideoProgressBar(holder);
        setupSlideTopSize(position, holder);
        setupFABButtonPosition(holder);

        return contentView;
    }

    private void setupVideoView(ViewHolder holder) {
        if (holder.videoView == null) {
            return;
        }

        holder.videoView.setBackgroundResource(R.color.background_color);
        holder.videoView.setVideoViewRecyclerListener(this);
    }

    private void setupVideoProgressBar(ViewHolder holder) {
        if (holder.videoViewLoading == null) {
            return;
        }

        holder.videoViewLoading.setVisibility(View.VISIBLE);
    }

    public void startVideoView() {
        if (mVideoView == null) {
            return;
        }

        mVideoView.setupAttributes(mResourceName);
        mVideoView.startCurrentPlayback();
    }

    @Override
    protected ViewHolder createViewHolder(View contentView) {
        ViewHolder viewHolder = new ViewHolder();
        if (contentView == null) {
            return viewHolder;
        }

        createPresentationViewHolder(viewHolder, contentView);

        viewHolder.videoView = (VideoViewRecycler) contentView.findViewById(R.id.presentation_video_view);
        viewHolder.videoViewLoading = (LinearLayout) contentView.findViewById(R.id.presentation_video_view_loading);

        contentView.setTag(viewHolder);

        return viewHolder;
    }

    protected class ViewHolder extends PresentationViewHolder {

        VideoViewRecycler videoView;

        LinearLayout videoViewLoading;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onVideoPlay() {
        if (mVideoView == null) {
            return;
        }

        mVideoViewLoading.setVisibility(View.GONE);
        mVideoView.setBackgroundDrawable(null);
    }
}