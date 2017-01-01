package com.android.lihou.agirl.ui.recyclerview;

import com.android.lihou.agirl.ui.recyclerview.base.ViewModel;
import com.android.lihou.agirl.ui.recyclerview.base.ViewModelAdapter;
import com.android.lihou.agirl.ui.recyclerview.viewmodel.LoadMoreModel;
import java.util.List;

/**
 * Created by Lihou.
 */

public class LoadMoreArranger extends Arranger {
    private ViewModel loadMoreModel;
    private boolean hasLoadMore;


    public LoadMoreArranger(ViewModelAdapter adapter) {
        super(adapter);
        loadMoreModel = new LoadMoreModel();
        hasLoadMore = true;
        insertModel(loadMoreModel);
    }


    public void loadMore(List<ViewModel> models) {
        insertAllModel(getViewModels().size() - 1, models);
    }


    public void hideLoadMoreModel() {
        if (hasLoadMore) {
            hasLoadMore = false;
            int loadMoreViewPosition = getViewModels().indexOf(loadMoreModel);
            getViewModels().remove(loadMoreModel);
            getAdapter().notifyItemRangeRemoved(loadMoreViewPosition, 1);
        }
    }


    public void showLoadMoreView() {
        if (!hasLoadMore) {
            hasLoadMore = true;
            insertModel(loadMoreModel);
        }
    }
}
