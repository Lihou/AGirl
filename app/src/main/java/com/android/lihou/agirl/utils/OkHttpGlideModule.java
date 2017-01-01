package com.android.lihou.agirl.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.support.v4.app.ActivityManagerCompat;

import com.android.lihou.agirl.data.net.HttpClientFactory;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;

import java.io.InputStream;

import okhttp3.OkHttpClient;

import static com.bumptech.glide.load.DecodeFormat.PREFER_RGB_565;
import static com.bumptech.glide.load.DecodeFormat.PREFER_ARGB_8888;

/**
 * Created by Lihou.
 */
public class OkHttpGlideModule implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        builder.setDecodeFormat(ActivityManagerCompat.isLowRamDevice(am) ?
                                PREFER_RGB_565 : PREFER_ARGB_8888);
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        final OkHttpClient httpClient
                = HttpClientFactory.getInstance(context.getApplicationContext());
        glide.register(
                GlideUrl.class,
                InputStream.class,
                new OkHttpUrlLoader.Factory(httpClient)
        );
    }
}
