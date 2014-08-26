package com.wildsmith.material.views;

import android.content.res.Resources;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;

import com.wildsmith.material.utils.ResourceHelper;

public class ViewHelper {

    public static float getPxFromDP(Resources resources, int dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
    }

    public static Drawable changeDrawableColor(int resourceId, int newImageColor) {
        Drawable drawable = ResourceHelper.getDrawable(resourceId);
        drawable.setColorFilter(newImageColor, Mode.MULTIPLY);
        return drawable;
    }
}