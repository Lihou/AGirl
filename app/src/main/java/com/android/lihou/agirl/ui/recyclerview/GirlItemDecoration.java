package com.android.lihou.agirl.ui.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

/**
 * Created by Lihou on 2016/9/28 0028.
 */

public class GirlItemDecoration extends RecyclerView.ItemDecoration {
    private static final int[] ATTRS = new int[] { android.R.attr.listDivider };

    private Drawable divider;


    public GirlItemDecoration(Context context) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        divider = a.getDrawable(0);
        a.recycle();
    }


    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State
            state) {
        super.onDraw(c, parent, state);
        if (parent.isAnimating()) return;
        final StaggeredGridLayoutManager lm
                = (StaggeredGridLayoutManager) parent.getLayoutManager();
        final int spanCount = lm.getSpanCount();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final StaggeredGridLayoutManager.LayoutParams params
                    = (StaggeredGridLayoutManager.LayoutParams) child.getLayoutParams();
            if (params.isFullSpan()) {
                continue;
            }
            final int right = lm.getDecoratedRight(child);
            final int bottom = lm.getDecoratedBottom(child);
            // draw the bottom divider
            divider.setBounds(lm.getDecoratedLeft(child),
                    bottom - divider.getIntrinsicWidth(), right, bottom);
            divider.draw(c);

            if (params.getSpanIndex() == spanCount - 1) continue;
            // draw the right edge divider
            divider.setBounds(right - divider.getIntrinsicWidth(),
                    lm.getDecoratedTop(child), right,
                    bottom - divider.getIntrinsicHeight());
            divider.draw(c);
        }
    }
}
