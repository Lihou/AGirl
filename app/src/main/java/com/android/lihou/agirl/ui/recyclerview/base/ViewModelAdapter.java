package com.android.lihou.agirl.ui.recyclerview.base;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

import static com.android.lihou.agirl.utils.Preconditions.checkNotNull;

/**
 * Created by Lihou.
 */

public class ViewModelAdapter extends RecyclerView.Adapter<BaseHolder> {
    private final List<ViewModel> viewModels;
    private final VHPresenter presenter;


    public ViewModelAdapter(@Nullable VHPresenter presenter) {
        this(new ArrayList<ViewModel>(), presenter);
    }


    public ViewModelAdapter(@Nullable List<ViewModel> viewModels,
                            @Nullable VHPresenter presenter) {
        this.viewModels = checkNotNull(viewModels);
        this.presenter = checkNotNull(presenter);
        this.presenter.setViewModels(viewModels);
    }


    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater
                = LayoutInflater.from(parent.getContext());
        return presenter.createViewHolder(inflater, parent, viewType);
    }


    @Override public void onViewRecycled(BaseHolder holder) {
        super.onViewRecycled(holder);
        presenter.unBindViewHolder(holder);
    }


    @Override public long getItemId(int position) {
        return viewModels.get(position).id();
    }


    @Override public void onBindViewHolder(BaseHolder holder, int position) {
        presenter.bindViewHolderAndModel(holder, viewModels.get(position));
    }


    @Override public int getItemCount() {
        return viewModels.size();
    }


    @Override public int getItemViewType(int position) {
        return viewModels.get(position).layoutId();
    }


    public List<ViewModel> getViewModels() {
        return viewModels;
    }
}
