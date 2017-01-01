package com.android.lihou.agirl.data.net;

import android.support.annotation.NonNull;

import com.android.lihou.agirl.utils.Config;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.android.lihou.agirl.utils.Preconditions.checkNotNull;

/**
 * Created by Lihou.
 */
public class RetroFactory {
    private RetroFactory() {
    }

    private static volatile Retrofit INSTANCE;

    public static Retrofit getInstance(@NonNull OkHttpClient client) {
        checkNotNull(client);
        Retrofit singleton = INSTANCE;
        if (singleton == null) {
            synchronized (RetroFactory.class) {
                singleton = INSTANCE;
                if (singleton == null) {
                    INSTANCE = singleton = createRetrofit(client);
                }
            }
        }
        return singleton;
    }


    private static Retrofit createRetrofit(@NonNull OkHttpClient client) {
        checkNotNull(client);
        final Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .serializeNulls()
                .create();
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
}
