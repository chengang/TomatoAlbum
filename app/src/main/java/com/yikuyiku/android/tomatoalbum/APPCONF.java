package com.yikuyiku.android.tomatoalbum;

import android.content.res.Resources;

public final class APPCONF {
    static private int gridSpanCount;
    static private int screenWidth;
    static private int screenHeight;
    static private int gridItemWidth;
    static private int gridItemHeightDefault;

    static {
        gridSpanCount = 3;
        screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
        gridItemWidth = screenWidth / gridSpanCount;
        gridItemHeightDefault = (int) (gridItemWidth * 0.5625); // 16:9
    }

    static public int getGridItemWidth() {
        return gridItemWidth;
    }

    static public int getGridItemHeightDefault() {
        return gridItemHeightDefault;
    }

    static public int getGridSpanCount() {
        return gridSpanCount;
    }

    static public int getScreenWidth() {
        return screenWidth;
    }

    static public int getScreenHeight() {
        return screenHeight;
    }
}
