package com.android.lihou.agirl.data.entity;

import android.support.annotation.Nullable;
import java.util.Date;

/**
 * Created by Lihou.
 */
public class Gank {
    /*
      "_id": "56d4f4f9421aa9647be5f908",
      "_ns": "ganhuo",
      "createdAt": "2016-03-01T09:48:41.472Z",
      "desc": "3.1",
      "publishedAt": "2016-03-01T12:09:30.687Z",
      "source": "chrome",
      "layoutId": "\u798f\u5229",
      "url": "http://ww3.sinaimg.cn/large/7a8aed7bjw1f1h4f51wbcj20f00lddih.jpg",
      "used": true,
      "who": "\u5f20\u6db5\u5b87"
    */
    public final String _id;
    public final String _ns;
    public final Date createdAt;
    public final String desc;
    public final Date publishedAt;
    public final String url;
    public final String type;
    @Nullable public final String source;
    public final String who;
    public final boolean used;
    public String[] images;


    public Gank(String id,
                String ns,
                Date createdAt,
                String desc,
                Date publishedAt,
                String url,
                String type,
                String source,
                String who,
                boolean used) {
        _id = id;
        _ns = ns;
        this.createdAt = createdAt;
        this.desc = desc;
        this.publishedAt = publishedAt;
        this.url = url;
        this.type = type;
        this.source = source;
        this.who = who;
        this.used = used;
    }
}
