package com.wildsmith.material.core;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;

public abstract class CoreMaterialListFragment extends ListFragment {

    protected CoreMaterialListFragmentListener mCallback;

    public interface CoreMaterialListFragmentListener {

        public void setCurrentFragmentTag(String tag);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (CoreMaterialListFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement CoreMaterialFragmentListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    @Override
    public void onResume() {
        super.onResume();

        mCallback.setCurrentFragmentTag(getFragmentTag());
    }

    public abstract String getFragmentTag();
}