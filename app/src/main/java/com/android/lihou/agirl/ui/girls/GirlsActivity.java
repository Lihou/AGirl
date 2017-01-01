package com.android.lihou.agirl.ui.girls;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.android.lihou.agirl.R;

/**
 * Created by Lihou.
 */

public class GirlsActivity extends AppCompatActivity {
    @BindView(R.id.girl_toolbar) Toolbar toolbar;


    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(null);
        ViewGroup rootView = (ViewGroup) this.findViewById(
                android.R.id.content);
        LayoutInflater.from(this).inflate(R.layout.activity_girls, rootView);
        ButterKnife.bind(this, rootView);
        setSupportActionBar(toolbar);
    }
}
