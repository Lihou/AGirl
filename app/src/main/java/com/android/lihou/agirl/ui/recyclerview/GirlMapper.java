package com.android.lihou.agirl.ui.recyclerview;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import com.android.lihou.agirl.R;
import com.android.lihou.agirl.data.entity.Gank;
import com.android.lihou.agirl.ui.recyclerview.base.Mapper;
import com.android.lihou.agirl.ui.recyclerview.base.ViewModel;
import com.android.lihou.agirl.ui.recyclerview.viewmodel.GirlModel;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;

/**
 * Created by Lihou.
 */

public class GirlMapper implements Mapper<List<ViewModel>, List<Gank>> {
    private final String[] colors
            = { "#fff5f5f5", "#ffeeeeee", "#ffe0e0e0" };
    private final int[] colorValues = new int[colors.length];

    private int count = 0;
    private Context context;
    private boolean needFindToday = true;


    public GirlMapper(Context context) {
        this.context = context;
        for (int i = 0; i < colors.length; i++) {
            colorValues[i] = Color.parseColor(colors[i]);
        }
    }


    @NonNull @Override public List<ViewModel> transform(List<Gank> girls) {
        if (girls == null || girls.isEmpty()) {
            return emptyList();
        }
        ArrayList<ViewModel> models = new ArrayList<>(girls.size());
        final int size = girls.size();
        for (int i = 0; i < size; i++) {
            final Gank girl = girls.get(i);
            String label = DateFormat.getDateFormat(context).format(girl.publishedAt);
            if (needFindToday && DateUtils.isToday(girl.publishedAt.getTime())){
                label = context.getString(R.string.label_today);
            }
            needFindToday = false;
            models.add(new GirlModel(
                    colorValues[++count % colorValues.length],
                    label,
                    girl.url,
                    girl.publishedAt)
            );
        }
        return models;
    }
}
