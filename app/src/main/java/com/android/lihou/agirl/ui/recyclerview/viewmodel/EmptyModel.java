package com.android.lihou.agirl.ui.recyclerview.viewmodel;

import android.support.v4.widget.Space;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.lihou.agirl.ui.recyclerview.base.BaseHolder;
import com.android.lihou.agirl.ui.recyclerview.base.VHWorker;
import com.android.lihou.agirl.ui.recyclerview.base.ViewId;
import com.android.lihou.agirl.ui.recyclerview.base.ViewModel;

/**
 * Created by Lihou.
 */

public class EmptyModel extends ViewModel {
    public static final int EMPTY_ID = ViewId.generateViewId();


    @Override public int layoutId() {
        return EMPTY_ID;
    }


    public static class EmptyHolder extends BaseHolder {
        public EmptyHolder(View itemView) {
            super(itemView);
            StaggeredGridLayoutManager.LayoutParams layoutParams
                    = new StaggeredGridLayoutManager.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, 0);
            layoutParams.setFullSpan(true);
            itemView.setLayoutParams(layoutParams);
        }
    }

    public static class EmptyWorker extends VHWorker {

        @Override
        public BaseHolder create(ViewGroup parent, LayoutInflater inflater) {
            Space space = new Space(parent.getContext());
            return new EmptyHolder(space);
        }


        @Override public void bind(BaseHolder viewHolder, ViewModel model) {
        }


        @Override public int type() {
            return EMPTY_ID;
        }
    }
}
