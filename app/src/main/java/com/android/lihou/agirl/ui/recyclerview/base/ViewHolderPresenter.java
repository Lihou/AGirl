package com.android.lihou.agirl.ui.recyclerview.base;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.List;

import static com.android.lihou.agirl.utils.Preconditions.checkNotNull;

/**
 * Created by Lihou.
 */

public final class ViewHolderPresenter implements VHPresenter {
    private final SparseArray<VHWorker> workers;
    private List<ViewModel> viewModels;


    private ViewHolderPresenter(SparseArray<VHWorker> workers) {
        this.workers = workers;
    }


    private ViewHolderPresenter(Builder builder) {
        this(builder.workers);
    }


    @Override public void setViewModels(List<ViewModel> models) {
        this.viewModels = models;
    }


    @Override public List<ViewModel> getViewModels() {
        return viewModels;
    }


    void setupWorker() {
        for (int i = 0; i < workers.size(); i++) {
            workers.valueAt(i).setPresenter(ViewHolderPresenter.this);
        }
    }


    @Override
    public BaseHolder createViewHolder(LayoutInflater inflater, ViewGroup parent, int type) {
        return workers.get(type).create(parent, inflater);
    }


    @Override
    public void bindViewHolderAndModel(BaseHolder viewHolder, ViewModel model) {
        VHWorker worker = workers.get(model.layoutId());
        if (worker != null) {
            worker.bind(viewHolder, model);
        }
    }


    @Override public void unBindViewHolder(final BaseHolder holder) {
        holder.unBind();
    }


    public static class Builder {
        private final SparseArray<VHWorker> workers;


        public Builder() {
            this.workers = new SparseArray<>();
        }


        public Builder addWorker(final VHWorker worker) {
            checkNotNull(worker, "worker must not be null");
            if (this.workers.get(worker.type()) != null) {
                throw new IllegalArgumentException("worker type has exits");
            }
            this.workers.put(worker.type(), worker);
            return this;
        }


        public ViewHolderPresenter build() {
            ViewHolderPresenter presenter = new ViewHolderPresenter(this);
            presenter.setupWorker();
            return presenter;
        }
    }
}
