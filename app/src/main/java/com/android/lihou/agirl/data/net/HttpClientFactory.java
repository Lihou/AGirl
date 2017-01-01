package com.android.lihou.agirl.data.net;

import android.content.Context;

import android.os.Build;
import android.os.Debug;
import android.support.annotation.NonNull;
import android.support.compat.BuildConfig;
import java.io.File;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import static com.android.lihou.agirl.utils.Preconditions.checkNotNull;
import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;
import static okhttp3.logging.HttpLoggingInterceptor.Level.NONE;

/**
 * Created by Lihou.
 */
public final class HttpClientFactory {
    private static final int DISK_CACHE_SIZE = 50 * 1024 * 1024;
    private static volatile OkHttpClient INSTANCE;
    private static final int TIME_OUT = 12;/*seconds*/


    @NonNull
    private static OkHttpClient createHttpClient(@NonNull Context application) {
        checkNotNull(application);
        File cacheDir = new File(application.getCacheDir(), "http");
        Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG ? BODY : NONE);
        return new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .cache(cache)
                .addInterceptor(interceptor)
                .build();
    }


    @NonNull
    public static OkHttpClient getInstance(@NonNull Context application) {
        checkNotNull(application);
        OkHttpClient singleton = INSTANCE;
        if (singleton == null) {
            synchronized (HttpClientFactory.class) {
                singleton = INSTANCE;
                if (singleton == null) {
                    INSTANCE = singleton = createHttpClient(application);
                }
            }
        }
        return singleton;
    }
}
