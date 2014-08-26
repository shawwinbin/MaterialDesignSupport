package com.wildsmith.material.playground;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

import com.wildsmith.material.R;
import com.wildsmith.material.core.CoreMaterialActivity;
import com.wildsmith.material.core.CoreMaterialListFragment;
import com.wildsmith.material.list.ListRow;
import com.wildsmith.material.playground.rows.PlaygroundActionBarCardRow;
import com.wildsmith.material.playground.rows.PlaygroundActionCardRow;
import com.wildsmith.material.playground.rows.PlaygroundCardListRow;
import com.wildsmith.material.playground.rows.PlaygroundElevationCardRow;
import com.wildsmith.material.playground.rows.PlaygroundNavigationCardRow;
import com.wildsmith.material.views.FloatingActionButton;

public class PlaygroundFragment extends CoreMaterialListFragment implements OnScrollListener {

    public static final String TAG = PlaygroundFragment.class.getSimpleName();

    private ListView mListView;

    private PlaygroundListRowAdapter mAdapter;

    private FloatingActionButton fabButton;

    private int mLastFirstVisibleItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.playground_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (view == null) {
            return;
        }

        setupListView();
        setupFabView(view);
    }

    private void setupFabView(View view) {
        fabButton = (FloatingActionButton) view.findViewById(R.id.fab_button);
    }

    private void setupListView() {
        if (mListView == null) {
            mListView = getListView();
            mListView.setOnScrollListener(this);
        }

        if (mAdapter == null) {
            mAdapter = new PlaygroundListRowAdapter(getActivity(), R.layout.playground_action_card_row);
        }

        if (mAdapter.getCount() == 0) {
            List<ListRow> rows = new ArrayList<ListRow>(20);
            int count = 0;
            for (int i = 0; i < 20; i++) {
                if (i % 3 == 0) {
                    count = 0;
                }

                PlaygroundCardListRow listRow = null;
                if (i == 0) {
                    listRow = new PlaygroundActionBarCardRow();
                } else if (count == 0) {
                    listRow = new PlaygroundActionCardRow();
                } else if (count == 1) {
                    listRow = new PlaygroundNavigationCardRow();
                } else {
                    listRow = new PlaygroundElevationCardRow();
                }

                listRow.setActivity(getActivity());
                rows.add(listRow);

                count++;
            }
            mAdapter.setRows(rows);
        }

        if (mListView.getAdapter() == null) {
            mListView.setAdapter(mAdapter);
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        final int currentFirstVisibleItem = mListView.getFirstVisiblePosition();

        if (currentFirstVisibleItem > mLastFirstVisibleItem) {
            getActivity().getActionBar().hide();
            ((CoreMaterialActivity) getActivity()).setupDrawerListRows(false);
            fabButton.hide();
        } else if (currentFirstVisibleItem < mLastFirstVisibleItem) {
            getActivity().getActionBar().show();
            ((CoreMaterialActivity) getActivity()).setupDrawerListRows(true);
            fabButton.show();
        }

        mLastFirstVisibleItem = currentFirstVisibleItem;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public String getFragmentTag() {
        return TAG;
    }
}