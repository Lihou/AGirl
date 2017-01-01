package com.android.lihou.agirl.ui.recyclerview.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.lihou.agirl.R;
import com.android.lihou.agirl.data.entity.Gank;
import com.android.lihou.agirl.ui.customtabs.CustomTabActivityHelper;
import com.android.lihou.agirl.ui.recyclerview.base.BaseHolder;
import com.android.lihou.agirl.ui.recyclerview.base.SimpleWorker;
import com.android.lihou.agirl.ui.recyclerview.base.ViewModel;

/**
 * Created by Lihou.
 */
public final class DailyModel extends ViewModel {
    public static final int FEED_LAYOUT_ID = R.layout.daily_item_view;
    public final Gank extra;


    public DailyModel(Gank extra) {this.extra = extra;}


    @Override public int layoutId() {
        return FEED_LAYOUT_ID;
    }


    public static class FeedViewHolder extends BaseHolder {
        public TextView title;

        public FeedViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.item_text_view);
        }


        @Override public void unBind() {

        }
    }

    public static class FeedWorker extends SimpleWorker {

        @Override
        public BaseHolder create(final ViewGroup parent, LayoutInflater inflater) {
            View view = inflater.inflate(FEED_LAYOUT_ID, parent, false);
            final FeedViewHolder holder = new FeedViewHolder(view);
            holder.itemView.setOnClickListener(v -> {
                final DailyModel model = (DailyModel) getPresenter()
                        .getViewModels()
                        .get(holder.getAdapterPosition());
                final Context host = holder.itemView.getContext();
                CustomTabActivityHelper.openCustomTab(
                        (Activity) host,
                        new CustomTabsIntent.Builder()
                                .enableUrlBarHiding()
                                .setShowTitle(true)
                                .setToolbarColor(
                                        ContextCompat.getColor(host,
                                                R.color.colorPrimary))
                                .build(),
                        Uri.parse(model.extra.url));
            });
            return holder;
        }


        @Override
        public void bind(BaseHolder viewHolder, ViewModel model) {
            FeedViewHolder holder = (FeedViewHolder) viewHolder;
            DailyModel DailyModel = (DailyModel) model;
            holder.title.setText(DailyModel.extra.desc);
        }


        @Override public int type() {
            return FEED_LAYOUT_ID;
        }
    }
}
