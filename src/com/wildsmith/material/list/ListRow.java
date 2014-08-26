package com.wildsmith.material.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public abstract class ListRow {

    public abstract View getView(LayoutInflater inflater, View contentView, Context context, int position);
}