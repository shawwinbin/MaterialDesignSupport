package com.wildsmith.material.core;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

public abstract class CoreMaterialFragment extends Fragment {

    protected CoreMaterialFragmentListener mCallback;

    public interface CoreMaterialFragmentListener {

        public void setCurrentFragmentTag(String tag);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (CoreMaterialFragmentListener) activity;
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