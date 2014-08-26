package com.wildsmith.material.list.expandable;

import java.util.List;

import com.wildsmith.material.list.ListRow;

public abstract class ExpandableListRow extends ListRow {

    private List<ListRow> mExpandableListChildren;

    public ListRow getChild(int position) {
        if (mExpandableListChildren == null || mExpandableListChildren.size() == 0) {
            return null;
        }

        return mExpandableListChildren.get(position);
    }

    public int getChildrenCount() {
        if (mExpandableListChildren == null || mExpandableListChildren.size() == 0) {
            return 0;
        }

        return mExpandableListChildren.size();
    }

    public void setChildren(List<ListRow> expandableListChildren) {
        mExpandableListChildren = expandableListChildren;
    }

    public List<ListRow> getChildren() {
        return mExpandableListChildren;
    }
}