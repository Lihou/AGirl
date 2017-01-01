package com.android.lihou.agirl.data.entity;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by Lihou.
 */

public final class Daily {
    @SerializedName("Android") public final List<Gank> android;
    @SerializedName("iOS") public final List<Gank> ios;
    @SerializedName("瞎推荐") public final List<Gank> recommendation;
    @SerializedName("拓展资源") public final List<Gank> resource;
    @SerializedName("福利") public final List<Gank> benefits;
    @SerializedName("休息视频") public final List<Gank> video;


    public Daily(List<Gank> android,
                 List<Gank> ios,
                 List<Gank> recommendation,
                 List<Gank> resource,
                 List<Gank> benefits,
                 List<Gank> video) {
        this.android = android;
        this.ios = ios;
        this.recommendation = recommendation;
        this.resource = resource;
        this.benefits = benefits;
        this.video = video;
    }
}
