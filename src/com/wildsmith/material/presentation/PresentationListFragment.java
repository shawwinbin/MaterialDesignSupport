package com.wildsmith.material.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.TextView;

import com.wildsmith.material.R;
import com.wildsmith.material.core.CoreMaterialActivity;
import com.wildsmith.material.core.CoreMaterialListFragment;
import com.wildsmith.material.list.ListRow;
import com.wildsmith.material.presentation.rows.PresentationVideoRow;
import com.wildsmith.material.utils.ResourceHelper;
import com.wildsmith.material.utils.StringUtils;
import com.wildsmith.material.views.FloatingActionButton;

public abstract class PresentationListFragment extends CoreMaterialListFragment implements OnScrollListener {

    protected ListView mListView;

    protected PresentationListRowAdapter mAdapter;

    protected FloatingActionButton mFABButton;

    private int mPosition;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.presentation_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (view == null) {
            return;
        }

        setupFab(view);
        setupActionBarTitle(view);
        setupListView(view);
        setupParentAdapterRows();
        setupFabAttributes(view);
    }

    private void setupFab(View view) {
        if (mFABButton == null) {
            mFABButton = (FloatingActionButton) view.findViewById(R.id.fab_button);
        }
    }

    private void setupListView(View view) {
        mListView = getListView();
        if (mListView == null) {
            return;
        }

        if (mListView.getAdapter() != null) {
            return;
        }

        if (mAdapter == null) {
            mAdapter = new PresentationListRowAdapter(getActivity(), R.layout.presentation_action_bar_row);
            mAdapter.setArguments((CoreMaterialActivity) getActivity(), mListView, mFABButton);
        }

        mListView.setAdapter(mAdapter);

        if (isListViewScrollable() == false) {
            mListView.setEnabled(false);
        }

        mListView.setOnScrollListener(this);
    }

    private void setupParentAdapterRows() {
        if (mAdapter == null || mListView == null) {
            return;
        }

        if (mAdapter.getCount() != 0) {
            return;
        }

        setupAdapterRows();
    }

    private void setupFabAttributes(View view) {
        if (mFABButton == null) {
            return;
        }

        mFABButton.setImageDrawableColor(ResourceHelper.getColor(R.color.presentation_fab_drawable_color));

        mFABButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mListView == null) {
                    return;
                }

                final int rowCount = mAdapter.getCount() - 1;
                int scrollPosition = mListView.getFirstVisiblePosition();
                if ((scrollPosition + 3) == rowCount) {
                    mFABButton.setVisibility(View.GONE);
                    mListView.setSelection(rowCount);
                    // mListView.smoothScrollToPosition(rowCount);
                    return;
                }

                mListView.smoothScrollByOffset(1);
            }
        });
    }

    public void setupActionBarTitle(View view) {
        if (view == null) {
            return;
        }

        TextView presentationActionBar = (TextView) view.findViewById(R.id.presentation_action_bar);
        if (presentationActionBar == null) {
            return;
        }

        final String actionBarTitle = ResourceHelper.getResString(getActionBarTitle());
        if (StringUtils.isEmpty(actionBarTitle)) {
            return;
        }

        presentationActionBar.setText(actionBarTitle);
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (firstVisibleItem > 0) {
            makeActionBarSolid();
        } else {
            makeActionBarTransparent();
        }

        final int actualVisibleItem = firstVisibleItem + 1;
        if (mPosition < actualVisibleItem) {
            ListRow mRow = mAdapter.getRow(actualVisibleItem);
            if (mRow instanceof PresentationVideoRow) {
                ((PresentationVideoRow) mRow).startVideoView();
            }
        }

        mPosition = actualVisibleItem;
    }

    private void makeActionBarSolid() {
        View root = getView();
        if (root == null) {
            return;
        }

        View actionBar = root.findViewById(R.id.presentation_action_bar);
        if (actionBar == null) {
            return;
        }

        actionBar.setVisibility(View.VISIBLE);
    }

    private void makeActionBarTransparent() {
        View root = getView();
        if (root == null) {
            return;
        }

        View actionBar = root.findViewById(R.id.presentation_action_bar);
        if (actionBar == null) {
            return;
        }

        actionBar.setVisibility(View.GONE);
    }

    public PresentationListRowAdapter getAdapter() {
        return mAdapter;
    }

    public FloatingActionButton getFAB() {
        return mFABButton;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {}

    public abstract boolean isListViewScrollable();

    public abstract void setupAdapterRows();

    public abstract int getActionBarTitle();
}