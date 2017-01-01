package com.android.lihou.agirl.data.entity;

import java.util.List;

/**
 * Created by Lihou.
 */
public final class GankRes<T> {
    public final List<String> category;
    public final boolean error;
    public final T results;


    public GankRes(List<String> category, boolean error, T results) {
        this.category = category;
        this.error = error;
        this.results = results;
    }
}
