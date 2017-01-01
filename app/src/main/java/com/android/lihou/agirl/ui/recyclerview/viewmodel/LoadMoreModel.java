package com.android.lihou.agirl.ui.recyclerview.viewmodel;

import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.android.lihou.agirl.R;
import com.android.lihou.agirl.ui.recyclerview.base.BaseHolder;
import com.android.lihou.agirl.ui.recyclerview.base.VHWorker;
import com.android.lihou.agirl.ui.recyclerview.base.ViewModel;

/**
 * Created by Lihou.
 */

public class LoadMoreModel extends ViewModel {
    public static final int LOAD_MORE_LAYOUT_ID = R.layout.infinite_loading;


    @Override public int layoutId() {
        return LOAD_MORE_LAYOUT_ID;
    }


    public static class LoadMoreHolder extends BaseHolder {
        ProgressBar progress;
        public LoadMoreHolder(View itemView) {
            super(itemView);
            progress = (ProgressBar) itemView;
            StaggeredGridLayoutManager.LayoutParams layoutParams
                    = (StaggeredGridLayoutManager.LayoutParams) this.itemView.getLayoutParams();
            layoutParams.setFullSpan(true);
            this.itemView.setLayoutParams(layoutParams);
        }
    }

    public static class LoadMoreWorker extends VHWorker {

        @Override
        public BaseHolder create(ViewGroup parent, LayoutInflater inflater) {
            View view = LayoutInflater.from(parent.getContext())
                                      .inflate(LOAD_MORE_LAYOUT_ID, parent, false);
            return new LoadMoreHolder(view);
        }


        @Override public void bind(BaseHolder viewHolder, ViewModel model) {

        }


        @Override public int type() {
            return LOAD_MORE_LAYOUT_ID;
        }
    }
}
