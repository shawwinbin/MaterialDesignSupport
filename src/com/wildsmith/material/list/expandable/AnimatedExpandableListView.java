package com.wildsmith.material.list.expandable;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

public class AnimatedExpandableListView extends ExpandableListView {

    public static final int ANIMATION_DURATION = 300;

    private AnimatedExpandableListAdapter adapter;

    public AnimatedExpandableListView(Context context) {
        super(context);
    }

    public AnimatedExpandableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimatedExpandableListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(ExpandableListAdapter adapter) {
        super.setAdapter(adapter);

        if (adapter instanceof AnimatedExpandableListAdapter) {
            this.adapter = (AnimatedExpandableListAdapter) adapter;
        } else {
            throw new ClassCastException(adapter.toString() + " must implement AnimatedExpandableListAdapter");
        }
    }

    public boolean expandGroupWithAnimation(int groupPos) {
        int groupFlatPos = getFlatListPosition(getPackedPositionForGroup(groupPos));
        if (groupFlatPos != -1) {
            int childIndex = groupFlatPos - getFirstVisiblePosition();
            if (childIndex < getChildCount()) {
                View v = getChildAt(childIndex);
                if (v.getBottom() >= getBottom()) {
                    adapter.notifyGroupExpanded(groupPos);
                    return expandGroup(groupPos);
                }
            }
        }

        adapter.startExpandAnimation(groupPos, 0);
        return expandGroup(groupPos);
    }

    public boolean collapseGroupWithAnimation(int groupPos) {
        int groupFlatPos = getFlatListPosition(getPackedPositionForGroup(groupPos));
        if (groupFlatPos != -1) {
            int childIndex = groupFlatPos - getFirstVisiblePosition();
            if (childIndex >= 0 && childIndex < getChildCount()) {
                View v = getChildAt(childIndex);
                if (v.getBottom() >= getBottom()) {
                    return collapseGroup(groupPos);
                }
            } else {
                return collapseGroup(groupPos);
            }
        }

        long packedPos = getExpandableListPosition(getFirstVisiblePosition());
        int firstChildPos = getPackedPositionChild(packedPos);
        int firstGroupPos = getPackedPositionGroup(packedPos);

        firstChildPos = firstChildPos == -1 || firstGroupPos != groupPos ? 0 : firstChildPos;

        adapter.startCollapseAnimation(groupPos, firstChildPos);

        adapter.notifyDataSetChanged();
        return isGroupExpanded(groupPos);
    }
}