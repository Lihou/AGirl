package com.android.lihou.agirl.ui.recyclerview.viewmodel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.lihou.agirl.R;
import com.android.lihou.agirl.ui.recyclerview.base.BaseHolder;
import com.android.lihou.agirl.ui.recyclerview.base.SimpleWorker;
import com.android.lihou.agirl.ui.recyclerview.base.ViewModel;

/**
 * Created by Lihou.
 */

public final class TitleModel extends ViewModel {
    public static final int TITLE_LAYOUT_ID = R.layout.daily_title_view;
    public String title;


    public TitleModel(String title) {
        this.title = title;
    }


    @Override public int layoutId() {
        return TITLE_LAYOUT_ID;
    }


    public static class TitleViewHolder extends BaseHolder {
        public TextView title;


        public TitleViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title_text_view);
        }
    }

    public static class TitleVHWorker extends SimpleWorker {

        @Override
        public BaseHolder create(ViewGroup parent, LayoutInflater inflater) {
            View view = inflater.inflate(TITLE_LAYOUT_ID, parent, false);
            return new TitleViewHolder(view);
        }


        @Override
        public void bind(BaseHolder viewHolder, ViewModel model) {
            TitleViewHolder holder = (TitleViewHolder) viewHolder;
            TitleModel titleModel = (TitleModel) model;
            holder.title.setText(titleModel.title);
        }


        @Override public int type() {
            return TITLE_LAYOUT_ID;
        }
    }
}
