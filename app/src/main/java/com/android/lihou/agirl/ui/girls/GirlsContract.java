package com.android.lihou.agirl.ui.girls;

import android.content.Context;
import com.android.lihou.agirl.BasePresenter;
import com.android.lihou.agirl.BaseView;
import com.android.lihou.agirl.ui.recyclerview.base.ViewModel;
import java.util.List;

/**
 * Created by Lihou.
 */

public interface GirlsContract {
    interface View extends BaseView{
        void hideLoadingProgress();
        Context getContext();
        void loadMore(List<ViewModel> models);
        void hideLoadMoreView();

    }

    interface Presenter extends BasePresenter<View>{
        void loadMoreData();
    }
}
