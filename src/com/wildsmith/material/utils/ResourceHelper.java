package com.wildsmith.material.utils;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.wildsmith.material.core.CoreMaterialApplication;

public class ResourceHelper {

    public static CharSequence getResText(int resourceId) {
        Context context = CoreMaterialApplication.getContext();
        if (context == null) {
            return null;
        }

        Resources resources = context.getResources();
        if (resources == null) {
            return null;
        }

        return resources.getText(resourceId);
    }

    public static String getResString(int resourceId) {
        Context context = CoreMaterialApplication.getContext();
        if (context == null) {
            return null;
        }

        Resources resources = context.getResources();
        if (resources == null) {
            return null;
        }

        return resources.getString(resourceId);
    }

    public static String[] getStringArray(int resourceId) {
        Context context = CoreMaterialApplication.getContext();
        if (context == null) {
            return null;
        }

        Resources resources = context.getResources();
        if (resources == null) {
            return null;
        }

        return resources.getStringArray(resourceId);
    }

    public static float getDimension(int resourceId) {
        Context context = CoreMaterialApplication.getContext();
        if (context == null) {
            return -1;
        }

        Resources resources = context.getResources();
        if (resources == null) {
            return -1;
        }

        return resources.getDimension(resourceId);
    }

    public static float getDimensionPixelSize(int resourceId) {
        Context context = CoreMaterialApplication.getContext();
        if (context == null) {
            return -1;
        }

        Resources resources = context.getResources();
        if (resources == null) {
            return -1;
        }

        return resources.getDimensionPixelSize(resourceId);
    }

    public static int getScreenHeight() {
        Context context = CoreMaterialApplication.getContext();
        if (context == null) {
            return -1;
        }

        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager == null) {
            return -1;
        }

        Display display = windowManager.getDefaultDisplay();
        if (display == null) {
            return -1;
        }

        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int height = metrics.heightPixels;

        return height;
    }

    public static int getScreenWidth() {
        Context context = CoreMaterialApplication.getContext();
        if (context == null) {
            return -1;
        }

        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager == null) {
            return -1;
        }

        Display display = windowManager.getDefaultDisplay();
        if (display == null) {
            return -1;
        }

        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int width = metrics.widthPixels;

        return width;
    }

    public static int getResourceIdentifier(String resourceName, String resourceType) {
        Context context = CoreMaterialApplication.getContext();
        if (context == null) {
            return 0;
        }

        Resources resources = context.getResources();
        if (resources == null) {
            return 0;
        }

        String packageName = context.getPackageName();
        if (StringUtils.isEmpty(packageName)) {
            return 0;
        }

        return resources.getIdentifier(resourceName, resourceType, packageName);
    }

    public static InputStream openAssetStream(String fileName) {
        Context context = CoreMaterialApplication.getContext();
        if (context == null) {
            return null;
        }

        AssetManager assets = context.getAssets();
        if (assets == null) {
            return null;
        }

        try {
            return assets.open(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static AssetFileDescriptor getAssetFileDescriptor(String assetName) {
        Context context = CoreMaterialApplication.getContext();
        if (context == null) {
            return null;
        }

        AssetManager assets = context.getAssets();
        if (assets == null) {
            return null;
        }

        try {
            return assets.openFd(assetName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Drawable getDrawable(int resourceId) {
        Context context = CoreMaterialApplication.getContext();
        if (context == null) {
            return null;
        }

        Resources resources = context.getResources();
        if (resources == null) {
            return null;
        }

        return resources.getDrawable(resourceId);
    }

    public static int getColor(int resourceId) {
        Context context = CoreMaterialApplication.getContext();
        if (context == null) {
            return -1;
        }

        Resources resources = context.getResources();
        if (resources == null) {
            return -1;
        }

        return resources.getColor(resourceId);
    }

    public static int getActionBarHeight() {
        Context context = CoreMaterialApplication.getContext();
        if (context == null) {
            return -1;
        }

        Theme theme = context.getTheme();
        if (theme == null) {
            return -1;
        }

        TypedArray styledAttributes = theme.obtainStyledAttributes(new int[] {android.R.attr.actionBarSize});
        if (styledAttributes == null) {
            return -1;
        }

        int actionBarHeight = (int) styledAttributes.getDimension(0, 0);

        styledAttributes.recycle();

        return actionBarHeight;
    }

    public static int getScreenOrientation() {
        Context context = CoreMaterialApplication.getContext();
        if (context == null) {
            return -1;
        }

        Resources resources = context.getResources();
        if (resources == null) {
            return -1;
        }

        Configuration configuration = resources.getConfiguration();
        if (configuration == null) {
            return -1;
        }

        return configuration.orientation;
    }
}