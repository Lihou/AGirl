package com.android.lihou.agirl.ui.girls;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import com.android.lihou.agirl.ui.recyclerview.GirlItemDecoration;
import com.android.lihou.agirl.ui.recyclerview.LoadMoreArranger;
import com.android.lihou.agirl.ui.recyclerview.LoadMoreScrollerListener;
import com.android.lihou.agirl.ui.recyclerview.base.VHPresenter;
import com.android.lihou.agirl.ui.recyclerview.base.ViewHolderPresenter;
import com.android.lihou.agirl.ui.recyclerview.base.ViewModel;
import com.android.lihou.agirl.ui.recyclerview.base.ViewModelAdapter;
import com.android.lihou.agirl.ui.recyclerview.viewmodel.EmptyModel;
import com.android.lihou.agirl.ui.recyclerview.viewmodel.EmptyModel.EmptyWorker;
import com.android.lihou.agirl.ui.recyclerview.viewmodel.LoadMoreModel;
import java.util.ArrayList;
import java.util.List;

import static com.android.lihou.agirl.ui.recyclerview.viewmodel.GirlModel.GirlWorker;
import static com.android.lihou.agirl.ui.recyclerview.viewmodel.LoadMoreModel.LoadMoreWorker;

/**
 * Created by Lihou.
 */

public class GirlsRecyclerView extends RecyclerView {
    private LoadMoreArranger loadmoreArranger;
    private onLoadMoreListener emptyLoadMoreListener = ()->{};
    private onLoadMoreListener loadMoreListener = emptyLoadMoreListener;


    public GirlsRecyclerView(Context context) {
        this(context,null);
    }


    public GirlsRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public GirlsRecyclerView(Context context,
                             @Nullable AttributeSet attrs,
                             int defStyle) {
        super(context, attrs, defStyle);

    }


    @Override protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setHasFixedSize(true);
        setItemAnimator(new DefaultItemAnimator());
        addItemDecoration(new GirlItemDecoration(getContext()));

        final List<ViewModel> viewModels = new ArrayList<>();
        viewModels.add(new EmptyModel());
        final VHPresenter adapterPresenter = new ViewHolderPresenter.Builder()
                .addWorker(new EmptyWorker())
                .addWorker(new GirlWorker())
                .addWorker(new LoadMoreWorker())
                .build();
        final ViewModelAdapter girlAdapter
                = new ViewModelAdapter(viewModels, adapterPresenter);
        loadmoreArranger = new LoadMoreArranger(girlAdapter);
        setAdapter(girlAdapter);
        final StaggeredGridLayoutManager layoutManager
                = new StaggeredGridLayoutManager(2, VERTICAL);
        setLayoutManager(layoutManager);
        addOnScrollListener(new LoadMoreScrollerListener(layoutManager) {
            @Override public void onLoadMore() {
                loadMoreListener.onLoadMore();
            }
        });
    }


    @Override protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        loadMoreListener = emptyLoadMoreListener;
    }


    public void setLoadMoreListener(onLoadMoreListener listener) {
        loadMoreListener = listener == null ? emptyLoadMoreListener : listener;
    }


    public interface onLoadMoreListener{
        void onLoadMore();
    }

    public void hideLoadMoreView(){
        loadmoreArranger.hideLoadMoreModel();
    }

    public void loadMore(List<ViewModel> girls){
        loadmoreArranger.loadMore(girls);
    }
}
