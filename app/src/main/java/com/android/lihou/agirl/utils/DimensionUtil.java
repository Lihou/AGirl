package com.android.lihou.agirl.utils;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by Lihou.
 */

public final class DimensionUtil {
    private DimensionUtil() {
        throw new AssertionError("not instantiate");
    }


    public static final float dp2px(int dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                Resources.getSystem().getDisplayMetrics());
    }


    public static final float sp2px(int sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                Resources.getSystem().getDisplayMetrics());
    }
}
