package com.wildsmith.material.interstitial;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wildsmith.material.R;
import com.wildsmith.material.core.CoreMaterialFragment;
import com.wildsmith.material.presentation.uitopics.WhatIsMaterialActivity;
import com.wildsmith.material.utils.ResourceHelper;
import com.wildsmith.material.views.FloatingActionButton;

public class InterstitialFragment extends CoreMaterialFragment {

    public static final String TAG = InterstitialFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.interstitial_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (view == null) {
            return;
        }

        setupInterstitialFAB(view);
    }

    private void setupInterstitialFAB(View view) {
        FloatingActionButton fabButton = (FloatingActionButton) view.findViewById(R.id.fab_button);
        if (fabButton == null) {
            return;
        }

        fabButton.setImageDrawableColor(ResourceHelper.getColor(R.color.interstitial_fab_color));

        fabButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                View root = getView();
                if (root == null) {
                    return;
                }

                Intent intent = new Intent(getActivity(), WhatIsMaterialActivity.class);
                getActivity().startActivity(intent);
            }
        });
    }

    @Override
    public String getFragmentTag() {
        return TAG;
    }
}