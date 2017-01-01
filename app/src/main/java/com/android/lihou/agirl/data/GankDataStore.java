package com.android.lihou.agirl.data;

import com.android.lihou.agirl.data.entity.Daily;
import com.android.lihou.agirl.data.entity.Gank;

import java.util.Date;
import java.util.List;

import rx.Observable;

/**
 * Created by Lihou.
 */
public interface GankDataStore {
    Observable<List<Gank>> getGanksByType(final String type, final int page);

    Observable<Daily> getGanksByDay(final Date day);
}
