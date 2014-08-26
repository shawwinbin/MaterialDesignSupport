package com.wildsmith.material.playground.rows;

import android.app.Activity;
import android.view.View;

import com.wildsmith.material.list.ListRow;

public abstract class PlaygroundCardListRow extends ListRow {

    protected Activity mActivity;

    public abstract View getTitleTextView(View view);

    public abstract View getInfoTextView(View view);

    public void setActivity(Activity mActivity) {
        this.mActivity = mActivity;
    }
}