package com.android.lihou.agirl.ui.recyclerview.base;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by Lihou.
 */

public abstract class ViewModel {
    private static final int DEFAULT_SPAN_COUNT = 1;

    public abstract int layoutId();

    public long id(){
        return RecyclerView.NO_ID;
    }

    public int spanCount() {
        return DEFAULT_SPAN_COUNT;
    }
}
