package com.android.lihou.agirl.data;

import android.app.Application;

import com.android.lihou.agirl.data.entity.Daily;
import com.android.lihou.agirl.data.entity.Gank;
import com.android.lihou.agirl.data.entity.GankRes;
import com.android.lihou.agirl.data.net.HttpClientFactory;
import com.android.lihou.agirl.data.net.RetroFactory;
import com.android.lihou.agirl.data.net.ServerException;
import com.android.lihou.agirl.data.net.api.GankService;
import com.android.lihou.agirl.utils.Config;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.Result;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

import static com.android.lihou.agirl.utils.Preconditions.checkNotNull;

/**
 * Created by Lihou.
 */
public class GankCloudStore implements GankDataStore {
    private final Retrofit retrofit;
    private final OkHttpClient httpClient;
    private final GankService service;

    private final Func1<GankRes<List<Gank>>, List<Gank>> mapData
            = response -> response.results;

    private final Func1<GankRes<Daily>, Daily> DailyMap
            = response -> response.results;


    public GankCloudStore(Application application) {
        httpClient = HttpClientFactory.getInstance(application);
        retrofit = RetroFactory.getInstance(httpClient);
        service = retrofit.create(GankService.class);
    }


    @Override
    public Observable<List<Gank>> getGanksByType(final String type, final int page) {
        return service.getGankByType(type, Config.DEFAULT_PAGE_SIZE, page)
                      .flatMap(result -> Observable.create(
                              new OnResponseSubscribe<List<Gank>>(result)))
                      .map(mapData);
    }


    @Override public Observable<Daily> getGanksByDay(final Date day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH) + 1;
        final int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        return service.getGankByDay(year, month, dayOfMonth)
                      .flatMap(result -> Observable.create(
                              new OnResponseSubscribe<Daily>(result)))
                      .map(DailyMap);
    }


    public class OnResponseSubscribe<T>
            implements Observable.OnSubscribe<GankRes<T>> {
        private final Result result;


        OnResponseSubscribe(Result result) {
            this.result = checkNotNull(result);
        }


        @SuppressWarnings("unchecked") @Override
        public void call(Subscriber<? super GankRes<T>> subscriber) {
            if (result.isError()) {
                subscriber.onError(result.error());
            }
            else if (!result.response().isSuccessful()) {
                subscriber.onError(new ServerException(result.response().code(),
                        result.response().errorBody()));
            }
            else {
                subscriber.onNext((GankRes<T>) result.response().body());
                subscriber.onCompleted();
            }
        }
    }
}