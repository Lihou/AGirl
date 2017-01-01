package com.android.lihou.agirl.ui.girls;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.android.lihou.agirl.R;
import com.android.lihou.agirl.ui.recyclerview.base.ViewModel;
import java.util.List;

/**
 * Created by Lihou.
 */

public class GirlsView extends CoordinatorLayout
        implements GirlsContract.View,
        GirlsRecyclerView.onLoadMoreListener {
    
    @BindView(R.id.girl_recyclerView) GirlsRecyclerView girlsRecyclerView;

    private GirlsPresenter presenter = new GirlsPresenter();


    public GirlsView(Context context) {
        this(context, null);
    }


    public GirlsView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GirlsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }


    @Override public void onAttachedToWindow() {
        super.onAttachedToWindow();
        presenter.bind(this);
        girlsRecyclerView.setLoadMoreListener(this);
    }


    @Override public void onDetachedFromWindow() {
        presenter.unBind();
        super.onDetachedFromWindow();
    }


    @Override public void hideLoadingProgress() {
        final View loadingView = findViewById(R.id.loading_layout);
        loadingView.animate()
                   .alpha(0f)
                   .setDuration(300L)
                   .setListener(new AnimatorListenerAdapter() {
                       @Override
                       public void onAnimationEnd(Animator animation) {
                           super.onAnimationEnd(animation);
                           loadingView.setVisibility(View.GONE);
                       }
                   });
    }


    @Override public void loadMore(List<ViewModel> girls) {
        girlsRecyclerView.loadMore(girls);
    }


    @Override public void hideLoadMoreView() {
        girlsRecyclerView.hideLoadMoreView();
    }


    @Override public void onLoadMore() {
        presenter.loadMoreData();
    }
}
