package com.wildsmith.material.views;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

import com.wildsmith.material.utils.ResourceHelper;

public class VideoViewRecycler extends SurfaceView implements Callback, OnPreparedListener, OnVideoSizeChangedListener {

    private static final int MAX_PLAY_BACK_ATTEMPTS = 5;

    private MediaPlayer mMediaPlayer;

    private Context mContext;

    private String mResourcePath;

    private boolean mIsSurfaceCreated;

    private boolean mIsPendingPlayback;

    private int mVideoWidth;

    private int mVideoHeight;

    private VideoViewRecyclerListener mListener;

    public interface VideoViewRecyclerListener {

        public void onVideoPlay();
    }

    @SuppressWarnings("deprecation")
    public VideoViewRecycler(Context context, AttributeSet attrs) {
        super(context, attrs);

        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        mContext = context;
    }

    public void setupAttributes(String resourceName) {
        int id = ResourceHelper.getResourceIdentifier(resourceName, "raw");
        mResourcePath = "android.resource://" + mContext.getPackageName() + "/" + id;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getDefaultSize(mVideoWidth, widthMeasureSpec);
        int height = getDefaultSize(mVideoHeight, heightMeasureSpec);
        if (mVideoWidth > 0 && mVideoHeight > 0) {
            if (mVideoWidth * height > width * mVideoHeight) {
                height = width * mVideoHeight / mVideoWidth;
            } else if (mVideoWidth * height < width * mVideoHeight) {
                width = height * mVideoWidth / mVideoHeight;
            }
        }

        setMeasuredDimension(width, height);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mIsSurfaceCreated = true;

        if (mIsPendingPlayback) {
            startCurrentPlayback();
            mIsPendingPlayback = false;
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mIsSurfaceCreated = false;

        stopCurrentPlayback();
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        if (mMediaPlayer == null || mMediaPlayer.isPlaying()) {
            return;
        }

        Log.i("VideoRow", "Starting video playback!");

        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();

        postDelayed(new VideoPlay(0), 250);
    }

    @Override
    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
        mVideoWidth = mp.getVideoWidth();
        mVideoHeight = mp.getVideoHeight();
        if (mVideoWidth != 0 && mVideoHeight != 0) {
            getHolder().setFixedSize(mVideoWidth, mVideoHeight);
        }
    }

    public void startCurrentPlayback() {
        if (mIsSurfaceCreated == false) {
            mIsPendingPlayback = true;
            return;
        }

        stopCurrentPlayback();

        try {
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setDisplay(getHolder());
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.setOnPreparedListener(this);
            mMediaPlayer.setOnVideoSizeChangedListener(this);
            mMediaPlayer.setDataSource(mContext, Uri.parse(mResourcePath));
            mMediaPlayer.prepareAsync();
        } catch (Exception e) {
            Log.i("VideoRow", "Issue trying to set the display!");
        }
    }

    private void stopCurrentPlayback() {
        if (mMediaPlayer == null || mMediaPlayer.isPlaying() == false) {
            return;
        }

        Log.i("VideoRow", "Stopping video playback!");

        mMediaPlayer.setLooping(false);
        mMediaPlayer.reset();
        mMediaPlayer.release();
        mMediaPlayer = null;
    }

    public void setVideoViewRecyclerListener(VideoViewRecyclerListener listener) {
        mListener = listener;
    }

    private class VideoPlay implements Runnable {

        private int mPlaybackAttempts;

        public VideoPlay(int playbackAttempts) {
            mPlaybackAttempts = playbackAttempts;
        }

        @Override
        public void run() {
            if (mMediaPlayer == null) {
                return;
            }

            if (mMediaPlayer.isPlaying() == false) {
                if (mPlaybackAttempts >= MAX_PLAY_BACK_ATTEMPTS) {
                    return;
                }

                postDelayed(new VideoPlay(mPlaybackAttempts++), 250);
                return;
            }

            if (mListener != null) {
                mListener.onVideoPlay();
            }
        }
    }
}