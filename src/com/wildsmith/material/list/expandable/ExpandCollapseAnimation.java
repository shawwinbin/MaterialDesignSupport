package com.wildsmith.material.list.expandable;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class ExpandCollapseAnimation extends Animation {

    private ExpandableListStateHolder mStateHolder;

    private View mView;

    private int mBaseHeight;

    private int mDelta;

    public ExpandCollapseAnimation(View v, int startHeight, int endHeight, ExpandableListStateHolder stateHolder) {
        mBaseHeight = startHeight;
        mDelta = endHeight - startHeight;
        mView = v;
        mStateHolder = stateHolder;

        mView.getLayoutParams().height = startHeight;
        mView.requestLayout();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        if (interpolatedTime < 1.0f) {
            int val = mBaseHeight + (int) (mDelta * interpolatedTime);
            mView.getLayoutParams().height = val;
            mStateHolder.setDummyHeight(val);
            mView.requestLayout();
        } else {
            int val = mBaseHeight + mDelta;
            mView.getLayoutParams().height = val;
            mStateHolder.setDummyHeight(val);
            mView.requestLayout();
        }
    }
}