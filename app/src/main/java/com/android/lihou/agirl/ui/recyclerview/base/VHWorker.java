package com.android.lihou.agirl.ui.recyclerview.base;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import static com.android.lihou.agirl.utils.Preconditions.checkNotNull;

/**
 * Created by Lihou.
 */

public abstract class VHWorker {
    private VHPresenter presenter;

    public abstract BaseHolder create(final ViewGroup parent, final LayoutInflater inflater);

    public abstract void bind(final BaseHolder viewHolder, final ViewModel model);

    public abstract int type();


    public void setPresenter(@NonNull VHPresenter presenter) {
        checkNotNull(presenter);
        this.presenter = presenter;
    }


    @NonNull public VHPresenter getPresenter() {
        if (presenter == null) {
            throw new IllegalStateException("VHPresenter should not be null");
        }
        return presenter;
    }
}
