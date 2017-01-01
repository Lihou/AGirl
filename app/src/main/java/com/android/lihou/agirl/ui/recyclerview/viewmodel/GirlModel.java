package com.android.lihou.agirl.ui.recyclerview.viewmodel;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.android.lihou.agirl.R;
import com.android.lihou.agirl.ui.daily.DailyActivity;
import com.android.lihou.agirl.ui.recyclerview.base.BaseHolder;
import com.android.lihou.agirl.ui.recyclerview.base.SimpleWorker;
import com.android.lihou.agirl.ui.recyclerview.base.ViewModel;
import com.android.lihou.agirl.ui.girls.GirlItemView;
import com.bumptech.glide.Glide;
import java.util.Date;

/**
 * Created by Lihou.
 */
public final class GirlModel extends ViewModel {
    public static final int GIRL＿LAYOUT_ID = R.layout.girl_item_view;
    public int width = 0;
    public int height = 0;
    public final int placeHolderColor;
    public final String label;
    public final String url;
    public final Date publishedAt;


    public GirlModel(int placeHolderColor,
                     String label,
                     String url,
                     Date publishedAt) {
        this.placeHolderColor = placeHolderColor;
        this.label = label;
        this.url = url;
        this.publishedAt = publishedAt;
    }


    @Override public int layoutId() {
        return GIRL＿LAYOUT_ID;
    }


    public static final class GirlHolder extends BaseHolder {
        GirlItemView girlItemView;


        public GirlHolder(GirlItemView itemView) {
            super(itemView);
            girlItemView = itemView;
        }


        @Override public void unBind() {
            this.girlItemView.reset();
            Glide.clear(this.girlItemView);
        }
    }

    public static class GirlWorker extends SimpleWorker {
        public GirlWorker() {
        }


        @Override
        public BaseHolder create(ViewGroup parent, LayoutInflater inflater) {
            GirlItemView view = (GirlItemView) inflater.inflate(
                    R.layout.girl_item_view, parent, false);
            final GirlHolder holder = new GirlHolder(view);
            view.setOnClickListener(v -> {
                final GirlModel model =
                        (GirlModel) getPresenter()
                                .getViewModels()
                                .get(holder.getAdapterPosition());
                DailyActivity.start((Activity) v.getContext(),
                        model.publishedAt);
            });
            return holder;
        }


        @Override public void bind(BaseHolder viewHolder, ViewModel model) {
            final GirlHolder holder = (GirlHolder) viewHolder;
            final GirlModel girlModel = (GirlModel) model;
            holder.girlItemView.bind(girlModel);
        }


        @Override public int type() {
            return GIRL＿LAYOUT_ID;
        }
    }
}
