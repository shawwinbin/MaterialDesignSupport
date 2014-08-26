package com.wildsmith.material.playground.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wildsmith.material.R;
import com.wildsmith.material.core.CoreMaterialFragment;

public class PlaygroundDetailFragment extends CoreMaterialFragment {

    public static final String TAG = PlaygroundDetailFragment.class.getSimpleName();

    private Boolean actionBarShowing;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        actionBarShowing = bundle.getBoolean("ActionBarShowing");

        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.playground_detail_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (view == null) {
            return;
        }

        setupActionBar(view);
    }

    private void setupActionBar(View view) {
        View fakeActionBar = view.findViewById(R.id.action_bar_view);
        if (actionBarShowing == false) {
            fakeActionBar.setVisibility(View.GONE);
        }
    }

    public static PlaygroundDetailFragment newInstance(Boolean actionBarShowing) {
        PlaygroundDetailFragment detailFragment = new PlaygroundDetailFragment();

        Bundle bundle = new Bundle();
        bundle.putBoolean("ActionBarShowing", actionBarShowing);
        detailFragment.setArguments(bundle);

        return detailFragment;
    }

    @Override
    public String getFragmentTag() {
        return TAG;
    }
}