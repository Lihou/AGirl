package com.android.lihou.agirl.ui.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import static com.android.lihou.agirl.utils.Preconditions.checkNotNull;

/**
 * Created by Lihou.
 */
public abstract class LoadMoreScrollerListener
        extends RecyclerView.OnScrollListener {
    private static final int VISIBLE_THRESHOLD = 5;

    private final StaggeredGridLayoutManager layoutManager;


    public LoadMoreScrollerListener(
            @NonNull StaggeredGridLayoutManager layoutManager) {
        this.layoutManager = checkNotNull(layoutManager);
    }


    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (dy < 0) {
            return;
        }
        final int totalItemCount = layoutManager.getItemCount();
        final int lastVisibleItemPosition = getLastVisibleItem(
                layoutManager.findLastVisibleItemPositions(null));
        if ((lastVisibleItemPosition + VISIBLE_THRESHOLD) > totalItemCount) {
            onLoadMore();
        }
    }


    private static int getLastVisibleItem(int[] lastVisibleItemPositions) {
        int maxSize = 0;
        for (int i = 0; i < lastVisibleItemPositions.length; i++) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i];
            }
            else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i];
            }
        }
        return maxSize;
    }


    public abstract void onLoadMore();
}
