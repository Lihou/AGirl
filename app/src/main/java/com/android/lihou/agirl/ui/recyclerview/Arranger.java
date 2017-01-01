package com.android.lihou.agirl.ui.recyclerview;

import com.android.lihou.agirl.ui.recyclerview.base.ViewModel;
import com.android.lihou.agirl.ui.recyclerview.base.ViewModelAdapter;
import java.util.List;

import static com.android.lihou.agirl.utils.Preconditions.checkNotNull;

/**
 * Created by Lihou.
 */

public class Arranger {
    private ViewModelAdapter adapter;
    private List<ViewModel> models;


    public Arranger(ViewModelAdapter adapter) {
        this.adapter = adapter;
        this.models = adapter.getViewModels();
    }


    public ViewModelAdapter getAdapter() {
        return adapter;
    }


    public List<ViewModel> getViewModels() {
        return models;
    }


    public void insertModel(ViewModel model) {
        final int startPosition = getViewModels().size();
        insertModel(startPosition, model);
    }


    public void insertModel(int index, ViewModel model) {
        getViewModels().add(index, model);
        this.adapter.notifyItemRangeInserted(index, getViewModels().size());
    }


    public void insertAllModel(List<ViewModel> models) {
        final int startPosition = getViewModels().size();
        insertAllModel(startPosition, models);
    }


    public void insertAllModel(int index, List<ViewModel> models) {
        getViewModels().addAll(index, models);
        this.adapter.notifyItemRangeInserted(index, models.size());
    }


    public void removeModel(ViewModel model) {
        final int position = getViewModels().indexOf(model);
        models.remove(model);
        adapter.notifyItemRemoved(position);
    }


    public void removeAllModel(List<ViewModel> models) {
        checkNotNull(models);
        if (models.size()==0){
            return;
        }
        final int firstPosition = getViewModels().indexOf(models.get(0));
        final int size = models.size();
        getViewModels().removeAll(models);
        adapter.notifyItemRangeRemoved(firstPosition,size);
    }
}
