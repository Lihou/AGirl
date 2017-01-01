package com.android.lihou.agirl;

/**
 * Created by Lihou.
 */

public interface BasePresenter<T extends BaseView> {
    void bind(T t);
    void unBind();
}
