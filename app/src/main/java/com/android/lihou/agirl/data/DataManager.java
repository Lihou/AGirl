package com.android.lihou.agirl.data;

import android.app.Application;
import com.android.lihou.agirl.data.entity.Daily;
import com.android.lihou.agirl.data.entity.Gank;
import java.util.Date;
import java.util.List;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by Lihou.
 */
public class DataManager implements GankDataStore {
    private static volatile GankDataStore INSTANCE;
    private GankDataStore gankDataStore;


    private DataManager() {}


    private DataManager(Application application) {
        gankDataStore = new GankCloudStore(application);
    }


    public static GankDataStore getInstance(Application application) {
        GankDataStore singleton = INSTANCE;
        if (singleton == null) {
            synchronized (DataManager.class) {
                singleton = INSTANCE;
                if (singleton == null) {
                    INSTANCE = singleton = new DataManager(application);
                }
            }
        }
        return singleton;
    }


    @Override
    public Observable<List<Gank>> getGanksByType(final String type, final int page) {
        return gankDataStore.getGanksByType(type, page)
                            .subscribeOn(Schedulers.io());
    }


    @Override public Observable<Daily> getGanksByDay(Date day) {
        return gankDataStore.getGanksByDay(day)
                            .subscribeOn(Schedulers.io());
    }
}
