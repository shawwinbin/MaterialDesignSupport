package com.wildsmith.material.list.expandable;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.AbsListView;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseExpandableListAdapter;

public abstract class AnimatedExpandableListAdapter extends BaseExpandableListAdapter {

    private static final int STATE_IDLE = 0;

    private static final int STATE_EXPANDING = 1;

    private static final int STATE_COLLAPSING = 2;

    private List<ExpandableListStateHolder> stateHolders;

    private AnimatedExpandableListView mExpandableListView;

    public AnimatedExpandableListAdapter(AnimatedExpandableListView expandableListView) {
        mExpandableListView = expandableListView;
    }

    private ExpandableListStateHolder getExpandableListStateHolder(int holderPosition) {
        if (stateHolders == null || stateHolders.isEmpty()) {
            stateHolders = new ArrayList<ExpandableListStateHolder>(8);
        }

        ExpandableListStateHolder stateHolder = null;
        if (holderPosition >= stateHolders.size()) {
            stateHolder = new ExpandableListStateHolder();
            stateHolders.add(stateHolder);
        } else {
            stateHolder = stateHolders.get(holderPosition);
        }

        return stateHolder;
    }

    @Override
    public final int getChildType(int holderPosition, int childPosition) {
        ExpandableListStateHolder stateHolder = getExpandableListStateHolder(holderPosition);
        if (stateHolder == null) {
            return -1;
        }

        if (stateHolder.isAnimating()) {
            return 0;
        } else {
            return getRealChildType(holderPosition, childPosition) + 1;
        }
    }

    @Override
    public final int getChildTypeCount() {
        return getRealChildTypeCount() + 1;
    }

    @Override
    public final int getChildrenCount(int holderPosition) {
        ExpandableListStateHolder stateHolder = getExpandableListStateHolder(holderPosition);
        if (stateHolder == null) {
            return -1;
        }

        if (stateHolder.isAnimating()) {
            return stateHolder.getFirstChildPosition() + 1;
        } else {
            return getRealChildrenCount(holderPosition);
        }
    }

    /**
     * Override {@link #getChildView(int, int, boolean, View, ViewGroup)} instead.
     */
    @Override
    public final View getChildView(final int holderPosition, int childPosition, boolean isLastChild, View convertView,
            final ViewGroup parent) {
        final ExpandableListStateHolder stateHolder = getExpandableListStateHolder(holderPosition);
        if (stateHolder == null) {
            return convertView;
        }

        if (stateHolder.isAnimating()) {
            if (convertView == null) {
                convertView = new ExpandableListDummyView(parent.getContext());
                convertView.setLayoutParams(new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT, 0));
            }

            if (childPosition < stateHolder.getFirstChildPosition()) {
                // The reason why we do this is to support the collapse
                // this group when the group view is not visible but the
                // children of this group are. When notifyDataSetChanged
                // is called, the ExpandableListView tries to keep the
                // list position the same by saving the first visible item
                // and jumping back to that item after the views have been
                // refreshed. Now the problem is, if a group has 2 items
                // and the first visible item is the 2nd child of the group
                // and this group is collapsed, then the dummy view will be
                // used for the group. But now the group only has 1 item
                // which is the dummy view, thus when the ListView is trying
                // to restore the scroll position, it will try to jump to
                // the second item of the group. But this group no longer
                // has a second item, so it is forced to jump to the next
                // group. This will cause a very ugly visual glitch. So
                // the way that we counteract this is by creating as many
                // dummy views as we need to maintain the scroll position
                // of the ListView after notifyDataSetChanged has been
                // called.
                convertView.getLayoutParams().height = 0;
                return convertView;
            }

            final ExpandableListDummyView dummyView = (ExpandableListDummyView) convertView;

            // Clear the views that the dummy view draws.
            dummyView.clearViews();

            // Set the style of the divider
            dummyView.setDivider(mExpandableListView.getDivider(), parent.getMeasuredWidth(), mExpandableListView.getDividerHeight());

            // Make measure specs to measure child views
            final int measureSpecW = MeasureSpec.makeMeasureSpec(parent.getWidth(), MeasureSpec.EXACTLY);
            final int measureSpecH = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);

            int totalHeight = 0;
            int clipHeight = parent.getHeight();

            final int len = getRealChildrenCount(holderPosition);
            for (int i = stateHolder.getFirstChildPosition(); i < len; i++) {
                View childView = getRealChildView(holderPosition, i, (i == len - 1), null, parent);
                childView.measure(measureSpecW, measureSpecH);
                totalHeight += childView.getMeasuredHeight();

                if (totalHeight < clipHeight) {
                    // we only need to draw enough views to fool the user...
                    dummyView.addFakeView(childView);
                } else {
                    dummyView.addFakeView(childView);

                    // if this group has too many views, we don't want to
                    // calculate the height of everything... just do a light
                    // approximation and break
                    int averageHeight = totalHeight / (i + 1);
                    totalHeight += (len - i - 1) * averageHeight;
                    break;
                }
            }

            Object o;
            int state = (o = dummyView.getTag()) == null ? STATE_IDLE : (Integer) o;

            if (stateHolder.isExpanding() && state != STATE_EXPANDING) {
                ExpandCollapseAnimation ani = new ExpandCollapseAnimation(dummyView, 0, totalHeight, stateHolder);
                ani.setDuration(AnimatedExpandableListView.ANIMATION_DURATION);
                ani.setAnimationListener(new AnimationListener() {

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        stopAnimation(holderPosition);
                        notifyDataSetChanged();
                        dummyView.setTag(STATE_IDLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {}

                    @Override
                    public void onAnimationStart(Animation animation) {}

                });
                dummyView.startAnimation(ani);
                dummyView.setTag(STATE_EXPANDING);
            } else if (stateHolder.isExpanding() == false && state != STATE_COLLAPSING) {
                if (stateHolder.getDummyHeight() == -1) {
                    stateHolder.setDummyHeight(totalHeight);
                }

                ExpandCollapseAnimation ani = new ExpandCollapseAnimation(dummyView, stateHolder.getDummyHeight(), 0, stateHolder);
                ani.setDuration(AnimatedExpandableListView.ANIMATION_DURATION);
                ani.setAnimationListener(new AnimationListener() {

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        stopAnimation(holderPosition);
                        mExpandableListView.collapseGroup(holderPosition);
                        notifyDataSetChanged();
                        stateHolder.setDummyHeight(-1);
                        dummyView.setTag(STATE_IDLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {}

                    @Override
                    public void onAnimationStart(Animation animation) {}

                });
                dummyView.startAnimation(ani);
                dummyView.setTag(STATE_COLLAPSING);
            }

            return convertView;
        } else {
            return getRealChildView(holderPosition, childPosition, isLastChild, convertView, parent);
        }
    }

    public void notifyGroupExpanded(int holderPosition) {
        ExpandableListStateHolder stateHolder = getExpandableListStateHolder(holderPosition);
        if (stateHolder == null) {
            return;
        }

        stateHolder.setDummyHeight(-1);
    }

    public void startExpandAnimation(int holderPosition, int firstChildPosition) {
        ExpandableListStateHolder stateHolder = getExpandableListStateHolder(holderPosition);
        if (stateHolder == null) {
            return;
        }

        stateHolder.setAnimating(true);
        stateHolder.setFirstChildPosition(firstChildPosition);
        stateHolder.setExpanding(true);
    }

    public void startCollapseAnimation(int holderPosition, int firstChildPosition) {
        ExpandableListStateHolder stateHolder = getExpandableListStateHolder(holderPosition);
        if (stateHolder == null) {
            return;
        }

        stateHolder.setAnimating(true);
        stateHolder.setFirstChildPosition(firstChildPosition);
        stateHolder.setExpanding(false);
    }

    private void stopAnimation(int holderPosition) {
        ExpandableListStateHolder stateHolder = getExpandableListStateHolder(holderPosition);
        if (stateHolder == null) {
            return;
        }

        stateHolder.setAnimating(false);
    }

    public int getRealChildType(int groupPosition, int childPosition) {
        return 0;
    }

    public int getRealChildTypeCount() {
        return 1;
    }

    public abstract View getRealChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent);

    public abstract int getRealChildrenCount(int groupPosition);
}