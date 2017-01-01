package com.android.lihou.agirl.ui.recyclerview.base;

/**
 * Created by Lihou.
 */

public interface Mapper<V,T> {
    V transform(T t);
}
