package com.wildsmith.material.list.expandable;

public class ExpandableListStateHolder {

    private boolean mIsAnimating;

    private boolean mIsExpanding;

    private int mFirstChildPosition;

    private int mDummyHeight = -1;

    public boolean isAnimating() {
        return mIsAnimating;
    }

    public void setAnimating(boolean mIsAnimating) {
        this.mIsAnimating = mIsAnimating;
    }

    public boolean isExpanding() {
        return mIsExpanding;
    }

    public void setExpanding(boolean mIsExpanding) {
        this.mIsExpanding = mIsExpanding;
    }

    public int getFirstChildPosition() {
        return mFirstChildPosition;
    }

    public void setFirstChildPosition(int mFirstChildPosition) {
        this.mFirstChildPosition = mFirstChildPosition;
    }

    public int getDummyHeight() {
        return mDummyHeight;
    }

    public void setDummyHeight(int mDummyHeight) {
        this.mDummyHeight = mDummyHeight;
    }
}