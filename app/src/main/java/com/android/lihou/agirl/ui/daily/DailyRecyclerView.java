package com.android.lihou.agirl.ui.daily;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import com.android.lihou.agirl.ui.recyclerview.Arranger;
import com.android.lihou.agirl.ui.recyclerview.base.ViewHolderPresenter;
import com.android.lihou.agirl.ui.recyclerview.base.ViewModel;
import com.android.lihou.agirl.ui.recyclerview.base.ViewModelAdapter;
import com.android.lihou.agirl.ui.recyclerview.viewmodel.DailyModel;
import com.android.lihou.agirl.ui.recyclerview.viewmodel.TitleModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lihou.
 */

public class DailyRecyclerView extends RecyclerView {
    private static final int FULL_SCREEN_ITEM_COUNT = 8;
    private Arranger controller;
    ViewModelAdapter adapter;

    public DailyRecyclerView(Context context) {
        this(context, null);
    }


    public DailyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public DailyRecyclerView(Context context,
                             @Nullable AttributeSet attrs,
                             int defStyle) {
        super(context, attrs, defStyle);
        List<ViewModel> viewModels = new ArrayList<>();
        ViewHolderPresenter factory
                = new ViewHolderPresenter.Builder()
                .addWorker(new DailyModel.FeedWorker())
                .addWorker(new TitleModel.TitleVHWorker())
                .build();
        adapter = new ViewModelAdapter(viewModels, factory);
        controller = new Arranger(adapter);
    }


    @Override protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        final LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), VERTICAL, false);
        setLayoutManager(layoutManager);
        setHasFixedSize(true);
        setAdapter(adapter);
    }


    void addData(List<ViewModel> models) {
        controller.insertAllModel(models);
    }

    void registerAdapterDataObserver(AdapterDataObserver observer){
        adapter.registerAdapterDataObserver(new AdapterDataObserver() {
            @Override public void onChanged() {
                super.onChanged();
                if (adapter.getItemCount() >= FULL_SCREEN_ITEM_COUNT) {
                    observer.onChanged();
                    adapter.unregisterAdapterDataObserver(this);
                }
            }
        });
    }
}
