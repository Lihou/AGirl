package com.android.lihou.agirl.data.net.api;

import com.android.lihou.agirl.data.entity.Daily;
import com.android.lihou.agirl.data.entity.GankRes;
import com.android.lihou.agirl.data.entity.Gank;
import java.util.List;
import retrofit2.adapter.rxjava.Result;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Lihou.
 */
public interface GankService {
    /*
    * http://gank.io/api/data/数据类型/请求个数/第几页
    */
    @GET("/api/data/{type}/{size}/{page}")
    Observable<Result<GankRes<List<Gank>>>> getGankByType(
            @Path("type") String type,
            @Path("size") int size,
            @Path("page") int page);

    /*
    * http://gank.io/api/day/年/月/日
    */

    @GET("/api/day/{year}/{month}/{day}")
    Observable<Result<GankRes<Daily>>> getGankByDay(
            @Path("year") int year,
            @Path("month") int month,
            @Path("day") int day);
}
