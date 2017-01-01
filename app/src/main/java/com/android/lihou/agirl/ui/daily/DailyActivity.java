package com.android.lihou.agirl.ui.daily;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.android.lihou.agirl.R;
import com.android.lihou.agirl.data.DataManager;
import com.android.lihou.agirl.data.entity.Daily;
import com.android.lihou.agirl.BaseActivity;
import com.android.lihou.agirl.ui.recyclerview.DailyMapper;
import com.android.lihou.agirl.ui.recyclerview.base.Mapper;
import com.android.lihou.agirl.ui.recyclerview.base.ViewModel;
import java.util.Date;
import java.util.List;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lihou.
 */
public class DailyActivity extends BaseActivity {
    private static final String TAG = DailyActivity.class.getSimpleName();
    private static final String CURRENT_DAY = "current_day";

    public final CompositeSubscription subscriptions
            = new CompositeSubscription();
    private Mapper<List<ViewModel>, Daily> dailyMapper;

    @BindView(R.id.daily_recycler) DailyRecyclerView recyclerView;
    @BindView(R.id.girl_toolbar) Toolbar toolbar;


    public static void start(Activity activity, Date day) {
        Intent intent = new Intent(activity, DailyActivity.class);
        intent.putExtra(CURRENT_DAY, day.getTime());
        activity.startActivity(intent);
    }


    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);
        ButterKnife.bind(this);
        setupToolbar();
        setupRecyclerView();
        getFeed();
    }


    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.daily_title);
    }


    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void setupRecyclerView() {
        recyclerView.registerAdapterDataObserver(// avoid over draw
                new RecyclerView.AdapterDataObserver() {
                    @Override public void onChanged() {
                        super.onChanged();
                            getWindow().setBackgroundDrawable(null);
                    }
                });
    }


    private void getFeed() {
        dailyMapper = new DailyMapper();
        Date currentDay = new Date();
        currentDay.setTime(getIntent().getLongExtra(CURRENT_DAY, -1));
        subscriptions.add(
                DataManager.getInstance(getApplication())
                           .getGanksByDay(currentDay)
                           .map(dailyMapper::transform)
                           .observeOn(AndroidSchedulers.mainThread())
                           .subscribe(new FeedSubscriber())
        );
    }


    @Override protected void onDestroy() {
        super.onDestroy();
        if (subscriptions.isUnsubscribed()) subscriptions.unsubscribe();
    }


    private class FeedSubscriber extends Subscriber<List<ViewModel>> {

        @Override public void onCompleted() {
        }


        @Override public void onError(Throwable e) {
        }


        @Override public void onNext(List<ViewModel> models) {
            recyclerView.addData(models);
        }
    }
}
