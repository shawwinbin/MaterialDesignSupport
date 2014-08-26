package com.wildsmith.material.presentation.devtopics;

import android.app.FragmentManager;
import android.os.Bundle;

import com.wildsmith.material.presentation.PresentationActivity;

public class CardViewActivity extends PresentationActivity {

    private CardViewFragment cardViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fm = getFragmentManager();
        cardViewFragment = (CardViewFragment) fm.findFragmentByTag(CardViewFragment.TAG);

        if (cardViewFragment == null) {
            cardViewFragment = CardViewFragment.newInstance();
            addFragment(cardViewFragment, CardViewFragment.TAG);
        }
    }
}