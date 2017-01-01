package com.android.lihou.agirl.utils;

/**
 * Borrowed from github.com/romannurik/muzei
 */
public class MathUtils {

    private MathUtils() { }

    public static float constrain(float min, float max, float v) {
        return Math.max(min, Math.min(max, v));
    }
}