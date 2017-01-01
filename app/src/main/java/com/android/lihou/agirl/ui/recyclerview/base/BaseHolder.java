package com.android.lihou.agirl.ui.recyclerview.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Lihou.
 */

public abstract class BaseHolder extends RecyclerView.ViewHolder {
    private ViewModel model;


    public BaseHolder(View itemView) {
        super(itemView);
    }


    public void unBind() {}
}
