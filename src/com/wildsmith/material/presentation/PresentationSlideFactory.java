package com.wildsmith.material.presentation;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.wildsmith.material.list.ListRow;
import com.wildsmith.material.presentation.rows.PresentationActionBarRow;
import com.wildsmith.material.presentation.rows.PresentationImageRow;
import com.wildsmith.material.presentation.rows.PresentationInformationRow;
import com.wildsmith.material.presentation.rows.PresentationListRow;
import com.wildsmith.material.presentation.rows.PresentationNavigationRow;
import com.wildsmith.material.presentation.rows.PresentationTitleRow;
import com.wildsmith.material.presentation.rows.PresentationVideoRow;

public class PresentationSlideFactory {

    private static final String TAG = PresentationSlideFactory.class.getSimpleName();

    public static List<ListRow> newSlideDeck(String[] slideClasses, String[] slideValues, int resId) {
        List<ListRow> rows = null;
        if (slideClasses == null || slideClasses.length == 0) {
            return rows;
        }

        if (slideValues == null || slideValues.length == 0) {
            return rows;
        }

        rows = new ArrayList<ListRow>(slideClasses.length);

        PresentationListRow row = null;
        try {
            for (int i = 0; i < slideClasses.length; i++) {
                Class<?> cls = Class.forName(slideClasses[i]);
                if (cls != null) {
                    Object potential = cls.newInstance();
                    if (potential instanceof PresentationListRow) {
                        row = (PresentationListRow) potential;
                        if (row instanceof PresentationActionBarRow) {
                            row.setArguments(new Object[] {slideValues[0]});
                            slideValues = subStringArray(slideValues, 0);
                        } else if (row instanceof PresentationInformationRow) {
                            row.setArguments(new Object[] {slideValues[0], slideValues[1]});
                            slideValues = subStringArray(slideValues, 1);
                        } else if (row instanceof PresentationNavigationRow) {
                            row.setArguments(new Object[] {slideValues[0], slideValues[1], slideValues[2], slideValues[3]});
                            ((PresentationNavigationRow) row).setNavigationResourceId(resId);
                            slideValues = subStringArray(slideValues, 3);
                        } else if (row instanceof PresentationVideoRow) {
                            row.setArguments(new Object[] {slideValues[0]});
                            slideValues = subStringArray(slideValues, 0);
                        } else if (row instanceof PresentationImageRow) {
                            row.setArguments(new Object[] {slideValues[0]});
                            slideValues = subStringArray(slideValues, 0);
                        } else if (row instanceof PresentationTitleRow) {
                            row.setArguments(new Object[] {slideValues[0]});
                            slideValues = subStringArray(slideValues, 0);
                        }
                    }

                    if (row == null) {
                        break;
                    }

                    rows.add(row);
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Could not create the requested slide deck." + e.getMessage());
        }

        return rows;
    }

    private static String[] subStringArray(String[] slideValues, int index) {
        if (slideValues == null || slideValues.length == 0) {
            return slideValues;
        }

        String[] newSlideValues = new String[slideValues.length];
        int newSlideValuesCount = 0;
        for (int i = 0; i < slideValues.length; i++) {
            if (i <= index) {
                continue;
            }

            newSlideValues[newSlideValuesCount] = slideValues[i];
            newSlideValuesCount++;
        }

        return newSlideValues;
    }
}