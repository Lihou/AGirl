package com.android.lihou.agirl.ui.recyclerview;

import android.support.annotation.NonNull;
import com.android.lihou.agirl.data.entity.Daily;
import com.android.lihou.agirl.data.entity.Gank;
import com.android.lihou.agirl.ui.recyclerview.base.Mapper;
import com.android.lihou.agirl.ui.recyclerview.base.ViewModel;
import com.android.lihou.agirl.ui.recyclerview.viewmodel.DailyModel;
import com.android.lihou.agirl.ui.recyclerview.viewmodel.TitleModel;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.*;

/**
 * Created by Lihou.
 */

public class DailyMapper implements Mapper<List<ViewModel>, Daily> {

    @NonNull @Override public List<ViewModel> transform(Daily daily) {
        if (daily == null) return emptyList();

        List<ViewModel> models = new ArrayList<>();
        if (daily.android != null) generate(models, "Android", daily.android);
        if (daily.ios != null) generate(models, "IOS", daily.ios);
        if (daily.recommendation != null) generate(models, "瞎推荐", daily.recommendation);
        if (daily.benefits != null) generate(models, "福利", daily.benefits);
        if (daily.video != null) generate(models, "休息视频", daily.video);
        if (daily.resource != null) generate(models, "拓展资源", daily.resource);
        return models;
    }


    private void generate(List<? super ViewModel> models, String title,
                          List<? extends Gank> dailies) {
        ViewModel titleModel = new TitleModel(title);
        models.add(titleModel);
        for (int i = 0, size = dailies.size(); i < size; i++) {
            models.add(new DailyModel(dailies.get(i)));
        }
    }
}

