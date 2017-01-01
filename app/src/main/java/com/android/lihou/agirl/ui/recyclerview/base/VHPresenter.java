package com.android.lihou.agirl.ui.recyclerview.base;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.List;

/**
 * Created by Lihou.
 */

public interface VHPresenter {
    BaseHolder createViewHolder(LayoutInflater inflater, ViewGroup parent, int type);

    void bindViewHolderAndModel(BaseHolder viewHolder, ViewModel model);

    void unBindViewHolder(final BaseHolder holder);

    void setViewModels(List<ViewModel> viewModels);

    List<ViewModel> getViewModels();
}

