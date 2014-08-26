package com.wildsmith.material.drawer;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.widget.TextView;

import com.wildsmith.material.R;
import com.wildsmith.material.core.CoreMaterialActivity;
import com.wildsmith.material.drawer.rows.ChildNavigationDrawerRow;
import com.wildsmith.material.drawer.rows.DividerDrawerRow;
import com.wildsmith.material.drawer.rows.ExpandableDrawerRow;
import com.wildsmith.material.drawer.rows.NavigationDrawerRow;
import com.wildsmith.material.list.ListRow;
import com.wildsmith.material.list.expandable.AnimatedExpandableListView;
import com.wildsmith.material.list.expandable.ExpandableListRow;
import com.wildsmith.material.utils.ResourceHelper;

public class DrawerListRowFactory {

    public static List<ListRow> createDrawerListRows(CoreMaterialActivity activity, AnimatedExpandableListView listView,
            TextView drawerListTitle, boolean includeActionBarRow) {
        List<ListRow> rows = new ArrayList<ListRow>(8);

        String[] drawerRowTitles = ResourceHelper.getStringArray(R.array.drawer_titles);
        String[] drawerRowImages = ResourceHelper.getStringArray(R.array.drawer_images);
        if (drawerRowTitles == null || drawerRowTitles.length == 0) {
            return rows;
        }

        if (includeActionBarRow) {
            drawerListTitle.setText(drawerRowTitles[0]);
        }

        int count = 0;
        for (String drawerRowTitle : drawerRowTitles) {
            if (drawerRowTitle == null) {
                count++;
                continue;
            }

            if (count == 0) {
                count++;
                continue;
            }

            final String drawerRowImage = drawerRowImages[count];

            count++;
            List<ListRow> rowChildren = createDrawerListRowChildren(activity, drawerRowTitle);
            if (rowChildren != null && rowChildren.size() != 0) {
                ExpandableListRow row = new ExpandableDrawerRow(listView, drawerRowTitle, drawerRowImage);
                row.setChildren(rowChildren);
                rows.add(row);
            } else {
                ListRow row = new NavigationDrawerRow(activity, drawerRowTitle, drawerRowImage);
                rows.add(row);
            }

            if (count <= drawerRowTitles.length - 1) {
                ListRow row = new DividerDrawerRow();
                rows.add(row);
            }
        }

        return rows;
    }

    private static List<ListRow> createDrawerListRowChildren(CoreMaterialActivity activity, String drawerRowTitle) {
        List<ListRow> childrenRows = new ArrayList<ListRow>(8);

        drawerRowTitle = drawerRowTitle.replace(" ", "_");
        drawerRowTitle = drawerRowTitle.toLowerCase(Locale.getDefault());
        String drawerRowClasses = drawerRowTitle + "_classes";
        drawerRowTitle = drawerRowTitle + "_titles";

        final int titlesResId = ResourceHelper.getResourceIdentifier(drawerRowTitle, "array");
        final int classesResId = ResourceHelper.getResourceIdentifier(drawerRowClasses, "array");
        if (titlesResId == 0 || classesResId == 0) {
            return childrenRows;
        }

        String[] childenRowTitles = ResourceHelper.getStringArray(titlesResId);
        if (childenRowTitles == null || childenRowTitles.length == 0) {
            return childrenRows;
        }

        for (String childenRowTitle : childenRowTitles) {
            if (childenRowTitle == null) {
                continue;
            }

            ListRow childRow = new ChildNavigationDrawerRow(activity, classesResId, childenRowTitle);
            childrenRows.add(childRow);
        }

        return childrenRows;
    }
}