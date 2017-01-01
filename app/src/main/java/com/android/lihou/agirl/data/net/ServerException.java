package com.android.lihou.agirl.data.net;

import okhttp3.ResponseBody;

/**
 * Created by Lihou.
 */

public class ServerException extends Throwable {
    public final int code;
    public final ResponseBody body;


    public ServerException(final int code, final ResponseBody body) {
        super();
        this.code = code;
        this.body = body;
    }
}
