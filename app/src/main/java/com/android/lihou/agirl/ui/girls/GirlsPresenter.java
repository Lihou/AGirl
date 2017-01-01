package com.android.lihou.agirl.ui.girls;

import android.app.Application;
import com.android.lihou.agirl.data.DataManager;
import com.android.lihou.agirl.data.Type;
import com.android.lihou.agirl.ui.recyclerview.GirlMapper;
import com.android.lihou.agirl.utils.Config;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;

import static com.android.lihou.agirl.utils.Preconditions.checkNotNull;

/**
 * Created by Lihou.
 */

public class GirlsPresenter implements GirlsContract.Presenter {
    private static final int FIRST_PAGE = 1;

    private int pageIndex = FIRST_PAGE;
    private boolean isFirstLoading = true;
    private boolean isNeedLoadMore = true;
    private boolean isLoading = false;

    GirlsContract.View girlView;
    private final CompositeSubscription subscriptions = new CompositeSubscription();
    private final PublishSubject<Integer> GirlSubject = PublishSubject.create();
    private GirlMapper girlMapper;

    @Override public void bind(GirlsContract.View view) {
        this.girlView = checkNotNull(view);
        girlMapper= new GirlMapper(girlView.getContext().getApplicationContext());
        subscriptions.add(
                GirlSubject.doOnNext(page -> isLoading = true)
                           .concatMap(page ->
                                   DataManager.getInstance(
                                           (Application) girlView.getContext()
                                                                 .getApplicationContext())
                                              .getGanksByType(Type.GIRL, page))
                           .observeOn(AndroidSchedulers.mainThread())
                           .doOnError(throwable -> isLoading = false)
                           .onErrorResumeNext(throwable -> {
                               return Observable.empty();
                           })
                           .observeOn(Schedulers.io())
                           .map(girlMapper::transform)
                           .observeOn(AndroidSchedulers.mainThread())
                           .doOnNext(girls -> {
                               if (girls.size() < Config.DEFAULT_PAGE_SIZE) {
                                   girlView.hideLoadMoreView();
                                   isNeedLoadMore = false;
                               }
                               else {
                                   increasePage();
                               }
                           })
                           .doOnNext(girls -> girlView.loadMore(girls))
                           .doOnNext(girls -> {
                               isLoading = false;
                               if (isFirstLoading) {
                                   isFirstLoading = false;
                                   girlView.hideLoadingProgress();
                               }
                           })
                           .subscribe()
        );
        GirlSubject.onNext(pageIndex);
    }

    @Override
    public void loadMoreData() {
        if (!isLoading && isNeedLoadMore) {
            GirlSubject.onNext(pageIndex);
        }
    }

    private void increasePage() {
        pageIndex++;
    }


    @Override public void unBind() {
        this.girlView = null;
        subscriptions.unsubscribe();
    }
}
